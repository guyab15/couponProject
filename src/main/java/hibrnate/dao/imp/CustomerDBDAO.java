package hibrnate.dao.imp;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;

import hibernate.exption.CouponProjectException;
import hibernate.exption.CouponProjectException.CustomerException;
import hibrnate.dao.CustomerDao;
import hibrnate.dao.HibernateFactory;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.Customer;
import hibrnate.util.HibernateUtil;

public class CustomerDBDAO extends HibernateFactory implements CustomerDao {
	public CustomerDBDAO() {
	}

	@Override
	public void createCustomer(Customer c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		//session.close();
	}

	@Override
	public void removeCustomer(Customer c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		//session.close();
	}

	@Override
	public void updateCustomer(Customer c) throws CustomerException {

		Customer cust = getCustomer(c.getId());
		if(cust.getCustName().equals(c.getCustName())){
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();	
		session.update(c);
		session.getTransaction().commit();
		}else{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Customer c where c.custName = '" + c.getCustName() + "'");
			List<Customer> list = (List<Customer>) query.list(); 
			if(list.size() == 0){
				session.update(c);
				session.getTransaction().commit();
				session.close();
			}else{
				throw new CouponProjectException.CustomerException("the name company is exisst");
			}
		}
	}

	@Override
	public Customer getCustomer(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = (Customer) session.get(Customer.class, id);
		//session.close();
		
		return customer; 
	}

	@Override
	public Collection<Customer> getAllCustomer() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Customer");
		//session.close();
		
		return query.list();
	}

	@Override
	public Collection<Coupon> getCoupon() {
		CouponDBDAO db = new CouponDBDAO();
		return db.getAllCoupons();
	}

	public Customer getCustomerByName(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Customer c where c.custName = '" +name+"'");
		Customer customer = (Customer) query.list().get(0);
		//session.close();
		
		return customer;
	}

	public boolean checkIfCustomerNameExist(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Customer c where c.custName= '" + name+"'");
		//session.close();
		
		return query.list().size() > 0;
	}

	public boolean checkIfCustomerBoughtCoupon(long cusId, long couponId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, cusId);
		List<Coupon> list = (List<Coupon>) customer.getCoupns();
		//session.close();
		for (Coupon c : list) {
			if (c.getId() == couponId)
				return true;
		}

		return false;
	}

	public void buyCoupon(long cusId, long couponId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, cusId);
		Coupon coupon = session.get(Coupon.class, couponId);
		customer.getCoupns().add(coupon);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public boolean login(String custName, String password) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Customer c  where c.custName = '" + custName+"'");
		List<Customer> list = query.list();
		//session.close();
		for (Customer customer : list) {
			if (customer.getPassword().equals(password))
				return true;
		}
		return false;
	}

}
