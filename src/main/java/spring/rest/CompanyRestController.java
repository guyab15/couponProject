package spring.rest;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import hibrnate.clients.ClientType;
import hibrnate.clients.CompanyFacade;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.CouponType;
import hibrnate.system.CouponSystem;
import spring.storage.FileSystemStorageService;

@RestController

public class CompanyRestController {
	@Autowired
	private FileSystemStorageService storage;
	
	private String pass;
	private String userName;
	private CompanyFacade companyFacad;

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void init() {
			companyFacad = (CompanyFacade) CouponSystem.getInstace().login(userName, pass, ClientType.COMPANY);
	}

	 
	 @RequestMapping("/company")
	 public ModelAndView companyPage(Model model) throws IOException{
		 Collection<Coupon> list = companyFacad.getAllCoupon();
		 ModelAndView mvc = new ModelAndView("company");
	     mvc.addObject("coupons",list);
	     mvc.addObject("userName",userName);
			return mvc;
	 }
	 
	 @GetMapping("/company/pic/{imageName:.+}")
	 public byte[] getImage(@PathVariable(value="imageName") String imageName) throws IOException{
		 byte[] image = storage.getImageBytes(imageName);
		 return image;
	 }
	 
	 @RequestMapping("/company/addCoupon")
	 public ModelAndView addCoupon(){
		 ModelAndView mvc  = new ModelAndView("companyAddCoupon");
		 mvc.addObject("userName",userName);
		 return mvc;
	 }
//	 @PostMapping("/company/delete")
//	 public void lala(HttpServletRequest request){
//		 Enumeration<String> params = request.getParameterNames();
//		 System.out.println("samting");
//		 while(params.hasMoreElements()){
//			 String paramName = params.nextElement();
//			 System.out.println("param name - "+paramName+" value- "+request.getParameter(paramName));
//		 }
//	 }
	 @PostMapping("/company/addCoupon/add")
	 public ResponseEntity<UrlMessage> add(@RequestParam("title") String title,
			 		@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			 		@RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			 		@RequestParam("amount") int amount,
			 		@RequestParam("message") String message,
	 				@RequestParam("price") double price,
	 				@RequestParam("type") String type,
	 				@RequestParam("image") MultipartFile image){
		Coupon coupon = new Coupon(title, startDate,endDate, amount, CouponType.valueOf(type), message, price, image.getOriginalFilename());
		System.out.println(coupon);
	 
		coupon.setCompany_id(companyFacad.getCompany().getId());
		 try {
			 companyFacad.createCouponForCompany(coupon, companyFacad.getCompany());
			 storage.store(image);
		} catch (Exception e) {
			UrlMessage urlMessage = new UrlMessage(e.getMessage());
			System.out.println(urlMessage);
			return new  ResponseEntity<UrlMessage>(urlMessage,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 return new ResponseEntity<UrlMessage>(new UrlMessage("the coupon "+coupon.getTitle()+" saved in the system"),HttpStatus.OK);
	 }
	
	 @PostMapping("/company/addCoupon/update")
	 public ResponseEntity<UrlMessage> updateCoupon(
			 	@RequestParam("id") long id,
			 	@RequestParam("title") String title,
		 		@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
		 		@RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
		 		@RequestParam("amount") int amount,
		 		@RequestParam("message") String message,
				@RequestParam("price") double price,
				@RequestParam("type") String type,
				@RequestParam("image") MultipartFile image){
		 if(image.isEmpty()){
			 System.out.println("image is empty");
			Coupon coupon = companyFacad.getCoupon(id);
			Coupon updateCoupon = new Coupon(id,title, startDate, endDate, amount, CouponType.valueOf(type), message, price, coupon.getImage());
			companyFacad.updateCoupon(updateCoupon);
		 }else{
			 //TODO  remove the old image !!!!
			 System.out.println("new image");
			 Coupon updateCoupon = new Coupon(id,title, startDate, endDate, amount, CouponType.valueOf(type), message, price, image.getName()); 
			 storage.store(image);
			 companyFacad.updateCoupon(updateCoupon);
		 }
		
		 
		 return new ResponseEntity<UrlMessage>(new UrlMessage("the coupon is update"),HttpStatus.OK);
		 
	 }
	 @PostMapping("/company/delete")
	 public ResponseEntity<?> delete(@RequestBody Coupon c){
		 		System.out.println(c);
			 Coupon coupon = companyFacad.getCoupon(c.getId());
			 companyFacad.removeCoupon(coupon);
			 return new ResponseEntity<UrlMessage>(new UrlMessage("the coupon "+c.getId()+" remove from system"),HttpStatus.OK);
		 
	 }
	 
	 @RequestMapping("/company/getCompany")
	public ResponseEntity<Company> getcompany() {
		Company company = companyFacad.getCompany();
		if (company == null)
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

}
