package hibrnate.dao;

import java.util.Collection;

import hibrnate.entity.Coupon;
import hibrnate.entity.CouponType;

public interface CouponDao {
	
public void createCoupon(Coupon c);
public void removeCoupon(Coupon c);
public void updateCoupon(Coupon c);
public Coupon getCoupon(long id);
public Collection<Coupon> getAllCoupons();
public Collection<Coupon> getCouponsByType(CouponType ct);

}
