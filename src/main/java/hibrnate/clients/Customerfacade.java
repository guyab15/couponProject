package hibrnate.clients;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import hibernate.exption.CouponProjectException.CustomerException;
import hibrnate.dao.imp.CouponDBDAO;
import hibrnate.dao.imp.CustomerDBDAO;
import hibrnate.entity.Coupon;
import hibrnate.entity.CouponType;
import hibrnate.entity.Customer;

public class Customerfacade implements CouponClientFacade {
	private CouponDBDAO couponDao = new CouponDBDAO();
	private CustomerDBDAO customerDao = new CustomerDBDAO();
	private long id;

	public Customerfacade() {
	}

	public Customerfacade(long id) {
		this.id = id;
	}
	public Customer getCustomer(){
		return customerDao.getCustomer(id);
	} 
	public Collection<Coupon> getAllCoupons(){
		return couponDao.getAllCoupons();
	}

	public Coupon getCouponById(long id){
		return couponDao.getCoupon(id);
	}
	public void purchaseCoupon(Coupon cop) throws CustomerException {

		if (!customerDao.checkIfCustomerBoughtCoupon(id, cop.getId())) {
			// check if coupon expires
			Date now = Date.valueOf(LocalDate.now().toString());
			Date end = (Date) cop.getEndDate();
			if (end.compareTo(now) == 0 || end.compareTo(now) > 0) {
				if (cop.getAmount() > 0) {
					customerDao.buyCoupon(id, cop.getId());
					cop.setAmount(couponDao.getCoupon(cop.getId()).getAmount() - 1);
					couponDao.updateCoupon(cop);
				} else {
					throw new CustomerException("קופון אזל מהמלאי");
				}
			} else {
				throw new CustomerException("קופון לא בתוקף");
			}
		} else {
			throw new CustomerException("הקופון כבר נקנה");
		}

	}

	public Collection<Coupon> getAllPurchasedCoupons() {
		return couponDao.getAllCouponByIdCustomer(id);
	}

	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type) {
		return couponDao.getAllCouponsOfCustomerByType(id, type);
	}

	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) {
		return couponDao.getAllCouponCustomerByPrice(id, price);
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType ct) {
		if (customerDao.checkIfCustomerNameExist(name)) {
			if (customerDao.login(name, password)) {
				Customer cus = customerDao.getCustomerByName(name);
				return new Customerfacade(cus.getId());
			} else {
				System.out.println("סיסמה לא נכונה");
				return null;
			}
		} else {
			System.out.println("שם לקוח לא קיים");
			return null;
		}

	}

}
