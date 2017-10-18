package spring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hibrnate.clients.ClientType;
import hibrnate.clients.CompanyFacade;
import hibrnate.entity.Company;
import hibrnate.system.CouponSystem;

@RestController
@RequestMapping("/company")
public class CompanyRestController {
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

	 
	 @RequestMapping()
	 public ModelAndView companyPage(Model model){
		 model.addAttribute("companyName",companyFacad.getCompany().getCompName());
		 model.addAttribute("email",companyFacad.getCompany().getEmail());
	 return new ModelAndView("company");
	 }
	 @RequestMapping(value ="/get",produces = MediaType.APPLICATION_JSON_VALUE)
	 public String getCompany(){
		 Company company =null;
		 company = companyFacad.getCompany();
		 if(company == null)
			 return "company null";
	
	
	 return company.toString();
	 }

	@RequestMapping("/getCompany")
	public ResponseEntity<Company> getcompany() {
		Company company = companyFacad.getCompany();
		if (company == null)
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

}
