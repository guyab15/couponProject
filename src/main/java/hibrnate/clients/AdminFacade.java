package hibrnate.clients;

import java.util.Collection;

import hibernate.exption.CouponProjectException.CompanyException;
import hibernate.exption.CouponProjectException.CustomerException;
import hibrnate.dao.imp.CompanyDBDAO;
import hibrnate.dao.imp.CouponDBDAO;
import hibrnate.dao.imp.CustomerDBDAO;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.Customer;

public class AdminFacade implements CouponClientFacade {
	private CompanyDBDAO companyDao = new CompanyDBDAO();
	private CustomerDBDAO customerDao = new CustomerDBDAO();
	private CouponDBDAO couponDao = new CouponDBDAO();
	
	public  AdminFacade() {	}

	public void createCompany(Company comp) throws Exception{
		
		if(!companyDao.checkComp_name(comp.getCompName())){
		companyDao.createCompany(comp);
		}else{
			throw new CompanyException("createCompany the name " + comp.getCompName() + " already exists");
		}
	}

	public void removeCompany(Company comp) {
		
		companyDao.remove(comp);
	}

	public void updateCompany(Company comp) throws CompanyException {
		companyDao.updateCompany(comp);
	}

	public Company getCompany(long id) throws Exception{
		Company company = companyDao.getCompany(id);
		if(company == null)
			throw new CompanyException("the company id "+id+" not exisst");
		return company;
	}

	public Collection<Company> getAllCompany() {
		return companyDao.getAllCompanies();
	}
	public Collection<Coupon> getAllCoupons(){
		return couponDao.getAllCoupons();
	}

	public void createCustomer(Customer cus) throws CustomerException {
		if(!customerDao.checkIfCustomerNameExist(cus.getCustName())){
				try {
				
					customerDao.createCustomer(cus);
				} catch (Exception e) {
					throw new CustomerException(e.getMessage());
				}
			}else{
				throw new CustomerException("the customer name exsist");
			}
	}

	public void removeCustomer(Customer cus) {
		customerDao.removeCustomer(cus);
	}

	public void updateCustomer(Customer cus) throws CustomerException {
		customerDao.updateCustomer(cus);
	}

	public Customer getCustomer(long id) throws CustomerException {
		Customer customer = customerDao.getCustomer(id);
		if(customer == null)
			throw new CustomerException("customer id "+id+" dont exsist");
		return customer;	
	}

	public Collection<Customer> getAllCustomer() {
		return customerDao.getAllCustomer();
	}

	@Override
	public  CouponClientFacade login(String name, String password, ClientType ct) {
		String userName ="1"; //"admin";
		String pass = "1";//"1234";
		if (userName.equals(name) && pass.equals(password)) {
			return new AdminFacade();
		}
		System.out.println("שם או סיסמה לא נכונים "+name+" "+password);
		return null;
	}

}
