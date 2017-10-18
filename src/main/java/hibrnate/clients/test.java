package hibrnate.clients;

public class test {
	public static void main(String[] args) {
		// CouponSystem sys = CouponSystem.getInstace();
		// CouponClientFacade client;
		// //create company
		// client = sys.login("admin", "1234", ClientType.ADMIN);
		// Company comp1 = new Company(1000, "red", "1234", "kjhg");
		// Company comp2 = new Company(2000, "blue", "1234", "ktuj");
		// Company comp3 = new Company(3000, "yellow", "1234", "cvbn");
		// Company comp4 = new Company(4000, "black", "1234", "wtyytf");
		// Company comp5 = new Company(5000, "green", "1234", "avbyu");
		// ((AdminFacade)client).createCompany(comp1);
		// ((AdminFacade)client).createCompany(comp2);
		// ((AdminFacade)client).createCompany(comp3);
		// ((AdminFacade)client).createCompany(comp4);
		// ((AdminFacade)client).createCompany(comp5);

		// //create coupons
		// client = sys.login("red", "1234", ClientType.COMPANY);
		// Coupon cop1 = new Coupon(1, "one", Date.valueOf("2017-01-01"),
		// Date.valueOf("2017-05-01"), 5, CouponType.CAMPING, "nidwi ej", 25.5,
		// null);
		// ((CompanyFacade)client).createCoupon(cop1);
		// Coupon cop2 = new Coupon(2, "two", Date.valueOf("2017-02-01"),
		// Date.valueOf("2017-05-28"), 2, CouponType.FOOD, "nidwi ej", 150,
		// null);
		// ((CompanyFacade)client).createCoupon(cop2);
		// Coupon cop3 = new Coupon(3, "tree", Date.valueOf("2017-03-17"),
		// Date.valueOf("2017-03-28"), 5, CouponType.HEALTH, "nidwi ej", 5.9,
		// null);
		// ((CompanyFacade)client).createCoupon(cop3);
		// //create customer
		// client = sys.login("admin", "1234", ClientType.ADMIN);
		// Customer cus1 = new Customer(30, "moshe", "1324");
		// Customer cus2 = new Customer(40, "david", "1324");
		// Customer cus3 = new Customer(50, "dodo", "1324");
		// ((AdminFacade)client).createCustomer(cus1);
		// ((AdminFacade)client).createCustomer(cus2);
		// ((AdminFacade)client).createCustomer(cus3);

		// buy coupon
		// //סיסמה לא נכונה
		// client = sys.login("dodo", "1234", ClientType.CUSTOMER);
		// Coupon cop3 = new Coupon(3, "tree", Date.valueOf("2017-03-17"),
		// Date.valueOf("2017-03-28"), 2, CouponType.HEALTH, "nidwi ej", 5.9,
		// null);
		// ((Customerfacade)client).purchaseCoupon(cop3);
		// //לא בתוקף
		// client = sys.login("dodo", "1324", ClientType.CUSTOMER);
		// Coupon cop3 = new Coupon(3, "tree", Date.valueOf("2017-03-17"),
		// Date.valueOf("2017-03-28"), 2, CouponType.HEALTH, "nidwi ej", 5.9,
		// null);
		// ((Customerfacade)client).purchaseCoupon(cop3);
		// // חסר במלאי (נקנו שני קופונים)
		// client = sys.login("dodo", "1324", ClientType.CUSTOMER);
		// Coupon cop2 = new Coupon(2, "two", Date.valueOf("2017-02-01"),
		// Date.valueOf("2017-05-28"), 5, CouponType.FOOD, "nidwi ej", 150,
		// null);
		// ((Customerfacade)client).purchaseCoupon(cop2);
		//
		// client = sys.login("moshe", "1324", ClientType.CUSTOMER);
		// ((Customerfacade)client).purchaseCoupon(cop2);
		//
		// client = sys.login("david", "1324", ClientType.CUSTOMER);
		// ((Customerfacade)client).purchaseCoupon(cop2);

		// מחיקת קופון חברה (מוחק גם היסטוריית קנייה של לקוחות)
		// client = sys.login("red", "1234", ClientType.COMPANY);
		// Coupon cop = new Coupon(2, "two", Date.valueOf("2017-02-01"),
		// Date.valueOf("2017-05-28"), 5, CouponType.FOOD, "nidwi ej", 150,
		// null);
		// ((CompanyFacade)client).removeCoupon(cop);

		// Coupon cop = new Coupon(301,
		// "parliament2",Date.valueOf("2016-01-02"), Date.valueOf("2016-02-02"),
		// 100, CouponType.TRAVELLING, "abc", 19.9, null);
		//
		// Set<Coupon> collection = new HashSet<Coupon>();
		//
		// try {
		// collection = (Set<Coupon>)( (CompanyFacade)
		// client).getAllCouponByDate(Date.valueOf("2016-02-02"));
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// for (Coupon coupon : collection) {
		// System.out.println(coupon);
		// }

	}
}
