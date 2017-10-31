package hibrnate.dao.imp;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;

import hibrnate.dao.CompanyDao;
import hibrnate.dao.HibernateFactory;
import hibrnate.entity.Company;
import hibrnate.entity.Coupon;
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
		session.delete(c);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void updateCompany(Company c) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		//session.close();
	}

	@Override
	public Company getCompany(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Company c where c.id= " + id);
		List<Company> list = query.list();
		System.out.println(list);
		System.out.println(list.isEmpty());
		//session.close();
		if(list.isEmpty())
			return null;
		
		
		return list.get(0);

	}

	public Company getCompanyByName(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Company c where c.compName = '" + name + "'");
		Company company; 
		try {
			company = (Company) query.list().get(0);
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
		//session.close();
		
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
