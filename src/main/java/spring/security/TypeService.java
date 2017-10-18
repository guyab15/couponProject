package spring.security;


import org.springframework.stereotype.Component;

import hibrnate.clients.AdminFacade;
import hibrnate.clients.CouponClientFacade;
import hibrnate.dao.imp.CompanyDBDAO;
import hibrnate.dao.imp.CustomerDBDAO;
import hibrnate.entity.Company;
import hibrnate.entity.Customer;
@Component
public class TypeService {

	private CompanyDBDAO companyDbdao = new CompanyDBDAO();
	private CustomerDBDAO customerDbdao = new CustomerDBDAO();
	private AdminFacade admin = new AdminFacade();
	
	public  TypeService() {}
     
	public Company companyfindUserName(String userName) {
		Company company = companyDbdao.getCompanyByName(userName);
		return company;
	}
	
	public Customer customerfindUserName(String userName) {
		Customer customer = customerDbdao.getCustomerByName(userName);
		return customer;
	}


}
