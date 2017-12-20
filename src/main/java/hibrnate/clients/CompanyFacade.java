package hibrnate.clients;
import java.sql.Date;
import java.util.Collection;

import hibernate.exption.CouponProjectException.CouponException;
import hibrnate.dao.imp.CompanyDBDAO;
import hibrnate.dao.imp.CouponDBDAO;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.CouponType;
public class CompanyFacade implements CouponClientFacade {
	private CouponDBDAO couponDao = new CouponDBDAO();
	private CompanyDBDAO companyDao = new CompanyDBDAO();
	private long id;
	
public CompanyFacade() {}
public CompanyFacade(long id){
	this.id = id;
}
public void createCouponForCompany(Coupon coupon,Company company) throws CouponException{
	if(!couponDao.checkIfCouponTitleExist(coupon.getTitle())){
			couponDao.createCoupon(coupon);
			couponDao.addCouponToCompany(company.getId(),couponDao.getCouponByTitle(coupon.getTitle()).getId() );
	}else{
		throw new CouponException("the title exsist , plese change the title");
		
	}
}
public void removeCoupon(Coupon coupon){
couponDao.removeCouponFromCompany(coupon.getId(),id);
couponDao.removeCouponFromCustomers(coupon.getId());
couponDao.removeCoupon(coupon);
}
public void updateCoupon(Coupon coupon){
couponDao.updateCoupon(coupon);	
}
public Company getCompany(){
	return companyDao.getCompany(id);
}   
public Coupon getCoupon(long iD){
	return couponDao.getCoupon(iD);
}

public Collection<Coupon> getAllCoupon(){
return couponDao.getAllCouponByCompanyId(id);
}
public Collection<Coupon> getAllCouponByType(CouponType type){
	return couponDao.getAllCouponsOfCompanyByType(id, type);
}
public Collection<Coupon> getAllCouponByPrice(double price){
	return couponDao.getAllCouponsOfCompanyByPrice(id, price);
}

//צפייה בקופונים של החברה עד תאריך מסויים
public Collection<Coupon> getAllCouponByDate(Date date){
	return couponDao.getAllCouponCompanyByDate(id, date);
}



	@Override
	public CouponClientFacade login(String name, String password, ClientType ct) {
		if(companyDao.checkComp_name(name)){
			if(companyDao.login(name, password)){
				Company company = companyDao.getCompanyByName(name);
				return new CompanyFacade(company.getId());
			}else{
				System.out.println("סיסמה לא נכונה");
				return null;
			}
		}else{
			System.out.println("שם חברה לא קיים");
			return null;
		}
		
	}
	

}
