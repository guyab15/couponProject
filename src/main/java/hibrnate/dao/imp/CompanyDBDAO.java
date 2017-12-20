package hibrnate.dao.imp;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;

import hibernate.exption.CouponProjectException;
import hibernate.exption.CouponProjectException.CompanyException;
import hibrnate.dao.CompanyDao;
import hibrnate.dao.HibernateFactory;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
import hibrnate.entity.Customer;
import hibrnate.util.HibernateUtil;

public class CompanyDBDAO extends HibernateFactory implements CompanyDao {

	public CompanyDBDAO() {
	}

	@Override
	public void createCompany(Company c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void remove(Company c) {
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = session.get(Company.class, c.getId());
	
		if(!company.getCoupons().isEmpty()){
			List<Coupon> list =  (List<Coupon>) company.getCoupons();
			System.out.println(list);
			for(Coupon coupon: list){
				System.out.println(coupon.getCustomers());
				if(!coupon.getCustomers().isEmpty()){
					List<Customer> customersList = coupon.getCustomers();
					System.out.println(customersList);
					for(Customer customer: customersList){
						customer.getCoupns().remove(coupon);
						session.update(customer);
					}
					
					coupon.getCustomers().clear();
					session.update(coupon);
				}

			}
			session.getTransaction().commit();
		}
		
		session.beginTransaction();
		Company co = session.get(Company.class, c.getId());
		System.out.println(co);
		session.delete(company);
		session.getTransaction().commit();
		//session.close();

	}

	@Override
	public void updateCompany(Company c) throws CompanyException {
		
		Company comp = getCompany(c.getId());
		if(comp.getCompName().equals(c.getCompName())){
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();	
		session.update(c);
		session.getTransaction().commit();
		}else{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Company c where c.compName = '" + c.getCompName() + "'");
			List<Company> list = (List<Company>) query.list(); 
			if(list.size() == 0){
				session.update(c);
				session.getTransaction().commit();
				session.close();
			}else{
				throw new CouponProjectException.CompanyException("the name company is exisst");
			}
		}
	}

	@Override
	public Company getCompany(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Company company = (Company)session.get(Company.class, id);

		return company;

	}

	public Company getCompanyByName(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Company c where c.compName = '" + name + "'");
		Company company; 
		try {
			company =  (Company) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		//session.close();
		
		
		return company;
	}

	@Override
	public Collection<Company> getAllCompanies() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Company ");
		List<Company> list = query.list();
		session.close();
		
		return list;
	}

	@Override
	public Collection<Coupon> getCoupons(Company c) {

		return c.getCoupons();
	}

	public boolean checkComp_name(String name) {
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Company c where c.compName = '" + name + "'");
		List<Company> list = query.list();
		session.getTransaction().commit();
		//session.close();

		return list.size() > 0;
	}

	@Override
	public boolean login(String compName, String password) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Company c where c.compName = '" + compName + "'");
		List<Company> list = query.list();
		//session.close();
		
		for (Company company : list) {
			if (company.getPassword().equals(password))
				return true;

		}

		return false;
	}

}
