package spring.rest;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import hibrnate.clients.ClientType;
import hibrnate.clients.CompanyFacade;
import hibrnate.entity.Company;
import hibrnate.system.CouponSystem;
import spring.security.RoleInit;

public class test {

	public static void main(String[] args) {
		CompanyFacade c = (CompanyFacade) CouponSystem.getInstace().login("comp 1", "11234", ClientType.COMPANY);
		//CompanyDBDAO c = new CompanyDBDAO();
		Company company = c.getCompany();
//		System.out.println(company);
		//Customerfacade c = (Customerfacade) CouponSystem.getInstace().login("a0", "01234", ClientType.CUSTOMER);
		//Customerfacade c = new Customerfacade();
		
//		Customer cus = c.getCustomer();
//		System.out.println(cus);
		
//		String s = "";
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			 s = mapper.writeValueAsString(company);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(s);
//		System.out.println(company);
		
		RoleInit rolesInit = new RoleInit();
		Collection<GrantedAuthority> r = rolesInit.getRoles();
		r.forEach(x-> {
			System.out.println(x);
		});
		
		
		
		
		
		
	}
	
	
}
