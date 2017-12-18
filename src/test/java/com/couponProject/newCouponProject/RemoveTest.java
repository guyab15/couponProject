package com.couponProject.newCouponProject;

import java.util.List;

import hibernate.exption.CouponProjectException.CompanyException;
import hibrnate.dao.imp.CompanyDBDAO;
import hibrnate.dao.imp.CouponDBDAO;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;

public class RemoveTest  {
public static void main(String[] args) {
	
	CompanyDBDAO comp = new CompanyDBDAO();
	CouponDBDAO coup = new CouponDBDAO();
	
	Company company = comp.getCompany(120);
	System.out.println("c o m p a n y");
	System.out.println(company);
//	List<Coupon> list =  (List<Coupon>) company.getCoupons();
//	for (Coupon coupon : list) {
//		coup.removeCouponFromCustomers(coupon.getId());
//		coup.removeCouponFromCompany(coupon.getId(), company.getId());
//		coup.removeCoupon(coupon);
//	}
//	try {
//		comp.updateCompany(company);
//	} catch (CompanyException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	//comp.remove(company);
}
	
}
