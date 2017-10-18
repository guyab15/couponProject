package com.couponProject.newCouponProject;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibrnate.dao.imp.CouponDBDAO;

public class Test {

	public static void main(String[] args) {
//		SessionFactory sf = new Configuration().configure().buildSessionFactory();
//		Session session = sf.openSession();
//		Transaction transaction = session.beginTransaction();
		
		//Query query = session.getNamedQuery("FROM Company ");
//		Company compp = new Company(555, "kkk", "1234", "com");
//		try {
//			//session.save("Company",compp);
//			session.update(compp);
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	    
//		Query query = session.createQuery("FROM Company ");
//		List<Company> company = query.list();
//		for (Company comp : company) {
//			System.out.println(comp);
//		}
		
//		Query query2 = session.createQuery("FROM Coupon ");
//		List<Coupon> coupons = query2.list();
//		for (Coupon coupon : coupons) {
//			System.out.println(coupon);
//		}
//		Query query3 = session.createQuery("FROM Customer ");
//		List<Customer> customer = query3.list();
//		for (Customer customers : customer) {
//			System.out.println(customers);
//		}
//		transaction.commit();
//		session.close();
//		sf.close();
		
//		CompanyDBDAO comp = new CompanyDBDAO();
//////		Company c =  comp.getCompanyByName("kkk");
//////		System.out.println(c);
////		System.out.println(comp.login("kkk", "1234"));
//		Company c = comp.getCompany(555);
//		System.out.println(c.getCoupns());
//		
//		CouponDBDAO couponDB = new CouponDBDAO();
		//couponDB.removeCouponFromCustomer_Coupon(12);
		//couponDB.removeCouponFromCompany_Coupon(123);
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
//		Coupon coupon= new Coupon (1, "first coupon" , new Date() , new Date() , 1000 , CouponType.CAMPING, "this is the new coupon",
//				2.12 , "www.google.com");
//		Coupon coupon2= new Coupon (2, "second coupon" , new Date() , new Date() , 1000 , CouponType.ELECTRICITY, "this is the onther new coupon",
//				2.12 , "www.google.com");
//		
//		Company company = new Company(1, "first company" , "123456" , "asaf@gmail.com");
//		Company company2 = new Company(2, "second company" , "654321" , "asaf2@gmail.com");
//		
//		Customer customer1= new Customer(1, "first customer", "1234");
//		Customer customer2= new Customer(2, "second customer", "4321");
//		
//		company.getCoupons().add(coupon);
//		company.getCoupons().add(coupon2);
//		
//		customer1.getCoupns().add(coupon);
//		customer2.getCoupns().add(coupon2);
//		
//		
		
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		session.beginTransaction();
//		
//		session.save(coupon);
//		session.save(coupon2);
//		session.save(company);
//		session.save(company2);
//		session.save(customer1);
//		session.save(customer2);
		
//		Customer c = new Customer( 1,"guy", "1234");
//		session.save(c);
		//Customer cus = (Customer) session.createQuery("from Customer where id = 290").list().get(0);
		//System.out.println(cus);
//		Query q = session.createQuery("from Coupon");
		//List<Coupon> lala =  (List<Coupon>) cus.getCoupns();
		
		//lala.forEach(x-> System.out.println(x) );
//		cus.setCoupns(lala);
//		session.save(cus);
//		
		
//		for(int i=102;i<150;i++){
//			Coupon c = new Coupon( "lala", new Date(), new Date(), 10, CouponType.FOOD, "blabla", 50.0, "tnt");
//			//System.out.println(c);
//			session.save(c);
//			//lala.add(c);
//			
//		}
		
//		cus.setCoupns(lala);
//		session.save(cus);
		/*
		Customer comp = new Customer();
		List<Customer> list = new ArrayList<>();
		list = session.createQuery("from Customer").list();
		comp = list.get(0);
		
		
		
		
		List<Coupon> l = new ArrayList<>();
		l = (List<Coupon>) comp.getCoupns();
		for(Coupon c : l){
			System.out.println(c);
		}
*/		//session.getTransaction().commit();
		
//		Company c = new Company(1,"elit", "1342", "mkmk");
//		Query q = session.createQuery("from Coupon");
//		ArrayList<Coupon> list = (ArrayList<Coupon>) q.list();
//		
//		 list.forEach(x-> System.out.println(x));
//		
//    	c.setCoupons(list);
//		session.save(c);
//		session.getTransaction().commit();
		
		
//		List<Coupon> l= (List<Coupon>)c.getAllCouponsOfCompanyByType(291, CouponType.FOOD);
		
//		l.forEach(x-> System.out.println(x));
		//CouponDBDAO c = new CouponDBDAO();
		//Company c = new CompanyDBDAO().getCompany(id)
		Date date = Date.valueOf("2017-09-14");
//		System.out.println(date);
//		CompanyFacade cf = new CompanyFacade(291);
//		List<Coupon> l =(List<Coupon>) cf.getAllCouponByDate(date);
		
//		CouponDBDAO cd = new CouponDBDAO();
//		Coupon c = new Coupon("gggg", date, date, 17, CouponType.SPORTS	, "hhh", 17.0, " ");
//		cd.createCoupon(c);
//		cd.addCouponToCompany(291, c.getId());
		
		
//		l.forEach(x->{
//			System.out.println(x.getEndDate().compareTo(date));
//			
//			
//			
//		});
		//l.forEach(x-> System.out.println(x));
		
		//System.out.println(l.size());
		
		
//		session.close();
//		sessionFactory.close();	
//		
//		CouponDBDAO c = new CouponDBDAO();
//		Coupon coupon =  session.get(Coupon.class, 1L);
//		coupon.getCustomers().forEach(x-> 
//			System.out.println(x)
//		);
	
		
		CouponDBDAO cd = new CouponDBDAO();
		cd.getAllCoupons().forEach(x->{
			System.out.println(x.getId());
		});
		
	}
		
}
