package spring.rest;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import hibrnate.clients.AdminFacade;
import hibrnate.clients.ClientType;
import hibrnate.entity.Company;
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

	@RequestMapping("/home")
	public ModelAndView adminPage() {
		return new ModelAndView("adminHome");
	}
	@RequestMapping("/company")
	public ModelAndView companyPage() {
		Collection<Company> list = adminFacad.getAllCompany();
		ModelAndView mvc = new ModelAndView("admin-company");
		mvc.addObject("companes",list);
		return mvc;
	}
	
	@RequestMapping("/customer")
	public ModelAndView customerPage() {
		return new ModelAndView("admin-customer");
	}

	@PostMapping(value = "/company/addCompany")
	public ModelAndView addCompany(@RequestParam(value = "compName") String compName,
			@RequestParam(value = "password") String password, @RequestParam(value = "email") String email,HttpSession session) {
		Company company = new Company();
		
		try {
			company.setCompName(compName);
			company.setPassword(password);
			company.setEmail(email);
			adminFacad.createCompany(company);
		} catch (Exception e) {
			ModelAndView mvc = new ModelAndView( new RedirectView("/admin/company?messageError"));
			session.setAttribute("message", e.getMessage());
		  return mvc;
		}
		ModelAndView mvc = new ModelAndView( new RedirectView("/admin/company?messageSuccess"));
		session.setAttribute("message", "Adding company "+compName+ " successfully ");
		 return mvc;
	}

//	@PostMapping("/company/getCompanyById")
//	public ModelAndView getCompanyById(@RequestParam("id") int id,HttpSession session) {
//		Company company ;
//		try {
//			company = adminFacad.getCompany(id);
//		} catch (Exception e) {
//			ModelAndView mvc = new ModelAndView( new RedirectView("/admin/company?messageError"));
//			session.setAttribute("message", e.getMessage());
//			return mvc;
//		}
//		System.out.println(company);
//		ModelAndView mvc = new ModelAndView("admin-company");
//		mvc.addObject("company",company);
//		mvc.
//		RedirectView r = new RedirectView("/admin/company?entity");
//		return mvc;
//	}
	@PostMapping("/company/getCompanyById")
	public ModelAndView getCompanyById(@RequestParam("id") int id,HttpSession session) {
		Company company ;
		try {
			company = adminFacad.getCompany(id);
		} catch (Exception e) {
			ModelAndView mvc = new ModelAndView( new RedirectView("/admin/company?messageError"));
			session.setAttribute("message", e.getMessage());
			return null;
		}
		System.out.println(company);
		RedirectView r = new RedirectView("/admin/company?entity");
		ModelAndView mvc = new ModelAndView("admin-company");
		mvc.addObject("company",company);
		
		
		return mvc;
	}
	
@GetMapping("/company/getcompany/{id}")
public Company getCompany(@PathVariable("id")long id){
	Company company = new Company();
	try {
		company = adminFacad.getCompany(id);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(company);
	return company;
	
}

	@PostMapping("/company/getAllCompanes")
	public ModelAndView getAllCompanes() {
		Collection<Company> list = adminFacad.getAllCompany();
		if (list == null)
			return  null;
		
		
		ModelAndView mvc = new ModelAndView("companes");
		mvc.addObject("companes",list);
		return mvc;

	}


	

}
