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
		
		List<Coupon> couponList = new ArrayList<Coupon>();
		Date dateS = Date.valueOf("2017-12-19");
		Date dateE = Date.valueOf("2018-12-19");
		CouponDBDAO coupondb = new CouponDBDAO();
		for (int i = 0; i <=100; i++) {
			Coupon coupon = new Coupon("lala", dateS, dateE, i, CouponType.TRAVELLING, "ggg", 69D, "tyger");
			coupondb.createCoupon(coupon);

			couponList.add(coupon);
		}

		Company company ;
		CompanyDBDAO cdd  = new CompanyDBDAO();
		for (int i = 0; i <=100; i++) {
			company = new Company();
			company.setCompName("comp "+i);
			company.setEmail("www");
			company.setPassword(i+"1234");
			cdd.createCompany(company);
			couponList.get(i).setCompany_id(company.getId());
			coupondb.addCouponToCompany(company.getId(), couponList.get(i).getId());
		}
		
		
		CustomerDBDAO cusdd = new CustomerDBDAO();
		for (int i = 0; i <=100; i++) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Customer customer; 
			customer = new Customer();
			customer.setCustName("a"+i);
			customer.setPassword(i+"1234");
			cusdd.createCustomer(customer);
			int num = i;
			ArrayList<Coupon> al = (ArrayList<Coupon>) couponList.stream().filter(x-> x.getId()>num).collect(Collectors.toList());
			al.forEach(x->{
				Coupon cc = session.get(Coupon.class, x.getId());
				cc.addCustomers(customer);
				session.update(cc);
			});
			
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
