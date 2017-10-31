package spring.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hibrnate.clients.AdminFacade;
import hibrnate.entity.Company;
import hibrnate.entity.Customer;

@Service
public class TypeUserDetailes implements UserDetailsService {

	Logger logger = Logger.getLogger(TypeUserDetailes.class);

	@Autowired
	TypeService typeService;

	@Autowired
	RoleInit rolesInit;

	@Autowired
	private HttpServletRequest request;
	private Company company;
	private Customer customer;
	private AdminFacade admin;
	private User userDetailes;

	public TypeUserDetailes() {
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Collection<GrantedAuthority> r; 
		String type = request.getParameter("type");

		if (type.equals("company")) {
			company = typeService.companyfindUserName(userName);
			r = rolesInit.getRolesCompany();
			if (company == null)
				return new User("www", "www", r);
			 userDetailes = new User(company.getCompName(), company.getPassword(), r);
		}
		if (type.equals("customer")) {
			customer = typeService.customerfindUserName(userName);
			r = rolesInit.getRolesCustomer();
			if (customer == null)
				return new User("www", "www", r);
			 userDetailes = new User(customer.getCustName(), customer.getPassword(), r);
		}
		if (type.equals("admin")) {
			r = rolesInit.getRolesAdmin();
			
			 userDetailes = new User("1", "1", r);
		}
		return userDetailes;
	}

}
