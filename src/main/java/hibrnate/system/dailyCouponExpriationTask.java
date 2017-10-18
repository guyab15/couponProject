package hibrnate.system;

import java.util.Collection;

import hibrnate.dao.imp.CouponDBDAO;
import hibrnate.entity.Coupon;

public class dailyCouponExpriationTask implements Runnable {
	private CouponDBDAO coupDao  = new CouponDBDAO();
	public dailyCouponExpriationTask(){}
 
	@Override
	public void run() {
		Collection<Coupon> collection = coupDao.getAllCouponExpired();
		for (Coupon coupon : collection) {
			coupDao.removeCouponFromCompany(coupon.getId(), coupon.getCompany().getId());
			coupDao.removeCouponFromCustomer(coupon.getId(),0);
			coupDao.removeCoupon(coupon);
		}
	}
	public void stopTask(){
		Thread.currentThread().interrupt();
	}

}
