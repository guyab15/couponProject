package hibrnate.dao.imp;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;

import hibrnate.dao.CouponDao;
import hibrnate.dao.HibernateFactory;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.CouponType;
import hibrnate.entity.Customer;
import hibrnate.util.HibernateUtil;

public class CouponDBDAO extends HibernateFactory implements CouponDao {

	public CouponDBDAO() {
	}

	@Override
	public void createCoupon(Coupon c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();

	}

	public Collection<Coupon> getAllCouponExpired() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Coupon c where c.endDate < NOW()");
		List<Coupon> list = query.list();
		session.close();

		return list;
	}

	public void addCouponToCompany(long idCompany, long idCoupon) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, idCompany);
		Coupon coupon = session.get(Coupon.class, idCoupon);
		//add 
		coupon.setCompany(company);
		company.getCoupons().add(coupon);
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void removeCoupon(Coupon c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		session.close();
	}

	public void removeCouponFromCustomer(long couponId, long customerId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, customerId);
		Coupon coupon = session.get(Coupon.class, couponId);
		customer.getCoupns().remove(coupon);

		session.getTransaction().commit();
		session.close();

	}

	public void removeCouponFromCustomers(long couponId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Coupon coupon = session.get(Coupon.class, couponId);
		List<Customer> list = (List<Customer>) coupon.getCustomers();
		list.forEach(x-> {
			removeCouponFromCustomer(couponId,x.getId());
			
		});


	}
	public void removeCouponFromCompany(long couponId, long companyId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, companyId);
		Coupon coupon = session.get(Coupon.class, couponId);
		company.getCoupons().remove(coupon);

		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateCoupon(Coupon c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Coupon getCoupon(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Coupon coupon = session.get(Coupon.class, id);
		session.close();

		return coupon;
	}

	@Override
	public Collection<Coupon> getAllCoupons() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Coupon ");
		List<Coupon> list = query.list();
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponByCompanyId(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, id);
		List<Coupon> list = (List<Coupon>) company.getCoupons();
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponByIdCustomer(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		List<Coupon> list = (List<Coupon>) customer.getCoupns();
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponsOfCompanyByType(long id, CouponType type) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, id);
		List<Coupon> list = (List<Coupon>) company.getCoupons().stream().filter(x -> x.getType() == type)
				.collect(Collectors.toList());
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponsOfCustomerByType(long id, CouponType type) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		List<Coupon> list = (List<Coupon>) customer.getCoupns().stream().filter(x -> x.getType() == type)
				.collect(Collectors.toList());
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponsOfCompanyByPrice(long id, double price) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, id);
		List<Coupon> list = (List<Coupon>) company.getCoupons().stream().filter(x -> x.getPrice() == price)
				.collect(Collectors.toList());
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponCustomerByPrice(long id, double price) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		List<Coupon> list = (List<Coupon>) customer.getCoupns().stream().filter(x -> x.getPrice() == price)
				.collect(Collectors.toList());
		session.close();

		return list;
	}

	public Collection<Coupon> getAllCouponCompanyByDate(long companyId, Date date) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, companyId);
		System.out.println(company.getCoupons().size());
		List<Coupon> list = (List<Coupon>) company.getCoupons().stream().filter(x -> x.getEndDate().compareTo(date) > 0)
				.collect(Collectors.toList());
		session.close();

		return list;

	}

	@Override
	public Collection<Coupon> getCouponsByType(CouponType ct) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Coupon> list = session.createQuery("from Coupon c where c.type = " + ct).list();
		session.close();

		return list;
	}

	public boolean checkIfCouponTitleExist(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Coupon c where c.title = '" + name + "'");
		List<Coupon> list = query.list();
		session.close();
		return list.size() > 0;
	}

}
