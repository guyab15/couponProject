package spring.rest;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hibrnate.clients.ClientType;
import hibrnate.clients.Customerfacade;
import hibrnate.entity.Coupon;
import hibrnate.system.CouponSystem;
import spring.storage.FileSystemStorageService;

@RestController

public class CustomerRestController {
	private String pass;
	private String userName;
	private Customerfacade customerFacad;
	
	@Autowired
	private FileSystemStorageService storage;

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void init() {
			customerFacad =  (Customerfacade) CouponSystem.getInstace().login(userName, pass, ClientType.CUSTOMER);
	}
	
	@RequestMapping("/customer")
	public ModelAndView getCustomerPage(){
		 Collection<Coupon> list = customerFacad.getAllPurchasedCoupons();
		 ModelAndView mvc = new ModelAndView("customer");
	     mvc.addObject("coupons",list);
	     mvc.addObject("userName",userName);
			return mvc;
	}
	 @GetMapping("/customer/pic/{imageName:.+}")
	 public byte[] getImage(@PathVariable(value="imageName") String imageName) throws IOException{
		 byte[] image = storage.getImageBytes(imageName);
		 return image;
	 }
	
	@RequestMapping("customer/buyCoupon")
	public ModelAndView getCustomerBuyCouponPage(){
		 Collection<Coupon> list = customerFacad.getAllCoupons();
		 ModelAndView mvc = new ModelAndView("customer-buyCoupon");
	     mvc.addObject("coupons",list);
	     mvc.addObject("userName",userName);
			return mvc;
	}
	
	@PostMapping("customer/buyCoupon")
	public ResponseEntity<?> buyCoupon(@RequestBody Coupon c){
		Coupon coupon = customerFacad.getCouponById(c.getId());
		try {
			
			customerFacad.purchaseCoupon(coupon);
		} catch (Exception e) {
			return new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()),HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<UrlMessage>(new UrlMessage("secsess buy coupon id- "+String.valueOf(coupon.getId())),HttpStatus.OK);
	}

}
