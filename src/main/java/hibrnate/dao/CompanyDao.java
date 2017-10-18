package hibrnate.dao;

import java.util.Collection;

import hibrnate.entity.Company;
import hibrnate.entity.Coupon;

public interface CompanyDao {

	public void createCompany(Company c);
	public void remove(Company c);
	public void updateCompany(Company c);
	public Company getCompany(long id);
	public Collection<Company> getAllCompanies();
	public Collection<Coupon> getCoupons(Company c);
	public boolean login(String compName, String password);
	
}
