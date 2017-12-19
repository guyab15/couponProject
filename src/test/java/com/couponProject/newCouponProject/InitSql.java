package com.couponProject.newCouponProject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import hibrnate.dao.imp.CompanyDBDAO;
import hibrnate.dao.imp.CouponDBDAO;
import hibrnate.dao.imp.CustomerDBDAO;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.CouponType;
import hibrnate.entity.Customer;
import hibrnate.util.HibernateUtil;

public class InitSql {
	public static void main(String[] args) {
		
		List<Coupon> list = new ArrayList<Coupon>();
		Date date = Date.valueOf("2017-09-14");
		CouponDBDAO coupondb = new CouponDBDAO();
		for (int i = 0; i <=100; i++) {
			Coupon coupon = new Coupon("lala", date, date, i, CouponType.TRAVELLING, "ggg", 69D, "tyger");
			coupondb.createCoupon(coupon);
			list.add(coupon);
			
		}

		Company company ;
		CompanyDBDAO cdd  = new CompanyDBDAO();
		for (int i = 0; i <=100; i++) {
			company = new Company();
			company.setCompName("comp "+i);
			company.setEmail("www");
			company.setPassword(i+"1234");
			cdd.createCompany(company);
			coupondb.addCouponToCompany(company.getId(), list.get(i).getId());
		}
		
		
		Customer customer; 
		CustomerDBDAO cusdd = new CustomerDBDAO();
		for (int i = 0; i <=100; i++) {
			customer = new Customer();
			customer.setCustName("a"+i);
			customer.setPassword(i+"1234");
			cusdd.createCustomer(customer);
			int num = i;
			ArrayList<Coupon> al = (ArrayList<Coupon>) list.stream().filter(x-> x.getId()>num).collect(Collectors.toList());
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Customer c = session.get(Customer.class, customer.getId());
			c.getCoupns().addAll(al);
			try {
				session.getTransaction().commit();
				
			} catch (Exception e) {
				System.out.println(customer.getId());
				al.forEach(x-> {
					System.out.println(x);
				});
			}
			session.close();
			
		}
		
		
		
		
	}
}
