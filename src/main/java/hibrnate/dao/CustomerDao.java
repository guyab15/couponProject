package hibrnate.dao;

import java.util.Collection;

import hibrnate.entity.Coupon;
import hibrnate.entity.Customer;

public interface CustomerDao {
public void createCustomer(Customer c);
public void removeCustomer(Customer c);
public void updateCustomer(Customer c);
public Customer getCustomer(long id);
public Collection<Customer> getAllCustomer();
public Collection<Coupon> getCoupon();
public boolean login(String custName,String password);
}
