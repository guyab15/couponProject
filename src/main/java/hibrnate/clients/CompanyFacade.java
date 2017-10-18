package hibrnate.clients;
import java.sql.Date;
import java.util.Collection;

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
public void createCoupon(Coupon coupon){
	if(!couponDao.checkIfCouponTitleExist(coupon.getTitle())){
			couponDao.createCoupon(coupon);
	}else{
		System.out.println("ישנה כותרת זהה");
	}
}
public void removeCoupon(Coupon coupon){
couponDao.removeCouponFromCompany(coupon.getId(),id);
couponDao.removeCouponFromCustomer(coupon.getId(),id);
couponDao.removeCoupon(coupon);
}
public void updateCoupon(Coupon coupon){
couponDao.updateCoupon(coupon);	
}
public Company getCompany(){
	return companyDao.getCompany(id);
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
				Company comp = companyDao.getCompanyByName(name);
				return new CompanyFacade(comp.getId());
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
