package hibrnate.clients;

import java.util.Collection;

import hibrnate.dao.imp.CompanyDBDAO;
import hibrnate.dao.imp.CustomerDBDAO;
import hibrnate.entity.Company;
import hibrnate.entity.Customer;

public class AdminFacade implements CouponClientFacade {
	private CompanyDBDAO companyDao = new CompanyDBDAO();
	private CustomerDBDAO customerDao = new CustomerDBDAO();

	public  AdminFacade() {	}

	public void createCompany(Company comp) {
		if(!companyDao.checkComp_name(comp.getCompName())){
		companyDao.createCompany(comp);
		}else{
			System.out.println("שם משתמש תפוס");
		}
	}

	public void removeCompany(Company comp) {
		//do
		companyDao.remove(comp);
	}

	public void updateCompany(Company comp) {
		companyDao.updateCompany(comp);
	}

	public Company getCompany(long id) {

		return companyDao.getCompany(id);
	}

	public Collection<Company> getAllCompany() {
		return companyDao.getAllCompanies();
	}

	public void createCustomer(Customer cus) {
		if(!customerDao.checkIfCustomerNameExist(cus.getCustName())){
			customerDao.createCustomer(cus);
			}else{
				System.out.println("שם משתמש תפוס");
			}
	}

	public void removeCustomer(Customer cus) {
		customerDao.removeCustomer(cus);
	}

	public void updateCustomer(Customer cus) {
		customerDao.updateCustomer(cus);
	}

	public Customer getCustomer(long id) {
		return customerDao.getCustomer(id);
	}

	public Collection<Customer> getAllCustomer() {
		return customerDao.getAllCustomer();
	}

	@Override
	public  CouponClientFacade login(String name, String password, ClientType ct) {
		String userName = "admin";
		String pass = "1234";
		if (userName.equals(name) && pass.equals(password)) {
			return new AdminFacade();
		}
		System.out.println("שם או סיסמה לא נכונים "+name+" "+password);
		return null;
	}

}
