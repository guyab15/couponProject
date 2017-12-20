package spring.rest;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import hibrnate.clients.AdminFacade;
import hibrnate.clients.ClientType;
import hibrnate.entity.Company;
import hibrnate.entity.Customer;
import hibrnate.system.CouponSystem;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	private String pass;
	private String userName;
	private AdminFacade adminFacad;
	Logger log = Logger.getLogger(AdminRestController.class);

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
		ModelAndView mvc = new ModelAndView("adminHome");
		mvc.addObject("userName", userName);
		return mvc;
	}

	@RequestMapping("/company")
	public ModelAndView companyPage() {
		Collection<Company> list = adminFacad.getAllCompany();
		ModelAndView mvc = new ModelAndView("admin-company");
		mvc.addObject("companes", list);
		mvc.addObject("userName", userName);
		return mvc;
	}

	@RequestMapping("/customer")
	public ModelAndView customerPage() {
		Collection<Customer> list = adminFacad.getAllCustomer();
		ModelAndView mvc = new ModelAndView("admin-customer");
		mvc.addObject("customers", list);
		mvc.addObject("userName", userName);
		return mvc;
	}

	@PostMapping(value = "/company/addCompany")
	public ModelAndView addCompany(@RequestParam(value = "compName") String compName,
			@RequestParam(value = "password") String password, @RequestParam(value = "email") String email,
			HttpSession session) {
		Company company = new Company();

		try {
			company.setCompName(compName);
			company.setPassword(password);
			company.setEmail(email);
			adminFacad.createCompany(company);
		} catch (Exception e) {
			ModelAndView mvc = new ModelAndView(new RedirectView("/admin/company?messageError"));
			session.setAttribute("message", e.getMessage());
			mvc.addObject("userName", userName);
			return mvc;
		}
		ModelAndView mvc = new ModelAndView(new RedirectView("/admin/company?messageSuccess"));
		session.setAttribute("message", "Adding company " + compName + " successfully ");
		mvc.addObject("userName", userName);
		return mvc;
	}

	@PostMapping(value = "/customer/addCustomer")
	public ResponseEntity<UrlMessage> addCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		try {
			adminFacad.createCustomer(customer);

		} catch (Exception e) {
			return new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UrlMessage>(new UrlMessage("customer " + customer.getCustName() + " saved"),
				HttpStatus.OK);
	}

	public String ifFirstIsEqual(String s) {
		if (s.charAt(0) == '=')
			return s.substring(1, s.length() - 1);
		return s;
	}

	public String ifLastIsEqual(String s) {
		if (s.charAt(s.length() - 1) == '=')
			return s.substring(0, s.length() - 1);
		return s;
	}

	@PostMapping("/company/getCompanyById")
	public ResponseEntity<?> getCompanyById(@RequestBody String s) {
		log.debug("guy avraham");
		s = ifFirstIsEqual(s);
		s = ifLastIsEqual(s);
		int id = Integer.parseInt(s);
		System.out.println(id);
		Company company;
		try {
			company = adminFacad.getCompany(id);
		} catch (Exception e) {
			ResponseEntity<UrlMessage> r = new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()),
					HttpStatus.NOT_ACCEPTABLE);
			System.out.println(r);
			return r;
		}
		ResponseEntity<Company> r = new ResponseEntity<Company>(company, HttpStatus.OK);
		System.out.println(r);
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@PostMapping("/customer/getCustomerById")
	public ResponseEntity<?> getCustomerById(@RequestBody String s) {
		s = ifFirstIsEqual(s);
		s = ifLastIsEqual(s);
		int id = Integer.parseInt(s);
		System.out.println(id);
		Customer customer;
		try {
			customer = adminFacad.getCustomer(id);
		} catch (Exception e) {
			ResponseEntity<UrlMessage> r = new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()),
					HttpStatus.NOT_ACCEPTABLE);
			System.out.println(r);
			return r;
		}
		ResponseEntity<Customer> r = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		System.out.println(r);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/company/getcompany/{id}")
	public Company getCompany(@PathVariable("id") long id) {
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
			return null;

		ModelAndView mvc = new ModelAndView("companes");
		mvc.addObject("companes", list);
		return mvc;

	}

	@PostMapping("/company/update")
	public ResponseEntity<?> update(@RequestBody Company company) {
		try {
			adminFacad.updateCompany(company);
		} catch (Exception e) {

			return new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@PostMapping("/customer/update")
	public ResponseEntity<UrlMessage> customerUpdate(@RequestBody Customer customer) {
		try {
			adminFacad.updateCustomer(customer);
		} catch (Exception e) {

			return new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UrlMessage>(new UrlMessage("the customer id " + customer.getId() + " update"),
				HttpStatus.OK);
	}

	@PostMapping("/company/delete")
	private ResponseEntity<UrlMessage> deleteCompany(@RequestBody Company company) {
		long id = company.getId();
		try {
			adminFacad.removeCompany(company);
		} catch (Exception e) {

			return new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UrlMessage>(new UrlMessage("the company id " + id + " Deleted forever"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/customer/delete")
	private ResponseEntity<UrlMessage> deleteCustomer(@RequestBody Customer customer) {
		long id = customer.getId();
		try {
			adminFacad.removeCustomer(customer);
		} catch (Exception e) {

			return new ResponseEntity<UrlMessage>(new UrlMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UrlMessage>(new UrlMessage("the customer id " + id + " Deleted forever"),
				HttpStatus.OK);
	}

}
