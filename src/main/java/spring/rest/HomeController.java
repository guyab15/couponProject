package spring.rest;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hibrnate.clients.AdminFacade;
import hibrnate.entity.Coupon;
import spring.storage.FileSystemStorageService;

@RestController
public class HomeController {
	@Autowired
	private FileSystemStorageService storage;
	
	private AdminFacade adminFacad = new AdminFacade();
	
	@RequestMapping("/")
	 public ModelAndView companyPage1(Model model) throws IOException{
		 Collection<Coupon> list = adminFacad.getAllCoupons();
		 ModelAndView mvc = new ModelAndView("home");
	     mvc.addObject("coupons",list);
			return mvc;
	 }

	 @RequestMapping("/home")
	 public ModelAndView companyPage(Model model) throws IOException{
		 Collection<Coupon> list = adminFacad.getAllCoupons();
		 ModelAndView mvc = new ModelAndView("home");
	     mvc.addObject("coupons",list);
			return mvc;
	 }
	 
	 @GetMapping("/home/pic/{imageName:.+}")
	 public byte[] getImage(@PathVariable(value="imageName") String imageName) throws IOException{
		 byte[] image = storage.getImageBytes(imageName);
		 return image;
	 }
}
