package spring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hibrnate.clients.ClientType;
import hibrnate.clients.Customerfacade;
import hibrnate.entity.Customer;
import hibrnate.system.CouponSystem;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	private String pass;
	private String userName;
	private Customerfacade customerFacad;

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void init() {
			customerFacad =  (Customerfacade) CouponSystem.getInstace().login(userName, pass, ClientType.CUSTOMER);
	}
	
	@RequestMapping("/getCustomer")
	public ResponseEntity<Customer> getcompany() {
		Customer customer = customerFacad.getCustomer();
		if (customer == null)
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	@RequestMapping("/get")
	public String get() {
		Customer customer = customerFacad.getCustomer();
		return customer.toString();
	}


}
