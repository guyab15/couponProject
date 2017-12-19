package spring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.hibernate.Session;

import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.Customer;
import hibrnate.util.HibernateUtil;
//@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class Info  {
	
	
	public static void main(String[] args) {
		ArrayList<String> rep = new ArrayList<>();
		
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		long idForCompany = 202;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, idForCompany);
		StringBuilder s = new StringBuilder();
		rep.add("company id = "+company.getId()+" have "+company.getCoupons().size()+" coupon");
		s.append("coupons ");
		List<Coupon> list = (List<Coupon>) company.getCoupons();
		for(Coupon x: list){
		s.append(x.getId()+",");
		}
		rep.add(s.toString());
		boolean flag = false;
		List<Coupon> list2 = (List<Coupon>) company.getCoupons();
		for(Coupon coupon: list2){
			flag = true;
			rep.add("coupon "+coupon.getId()+" have "+coupon.getCustomers().size()+" customer");
		}
		s = new StringBuilder();
		s.append("customer : ");
		if(flag){
			for(Coupon coupon: list2){
				final List<Customer> l = coupon.getCustomers();
				for(Customer c: l)
					s.append(c.getId()+" ");
			}
		}
		rep.add(s.toString());
		rep.forEach(x->{
			System.out.println(x);
		});
		
	}
	
	
	
	
}
