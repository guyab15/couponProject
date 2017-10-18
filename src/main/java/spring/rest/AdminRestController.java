package spring.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hibrnate.clients.AdminFacade;
import hibrnate.clients.ClientType;
import hibrnate.system.CouponSystem;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	private String pass;
	private String userName;
	private AdminFacade adminFacad;

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void init() {
		adminFacad = (AdminFacade) CouponSystem.getInstace().login(userName, pass, ClientType.ADMIN);
	}

	@RequestMapping()
	 public ModelAndView companyPage(){
	 return new ModelAndView("admin");
	 }
	
	
}
