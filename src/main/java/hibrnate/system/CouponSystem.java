package hibrnate.system;

import hibrnate.clients.AdminFacade;
import hibrnate.clients.ClientType;
import hibrnate.clients.CompanyFacade;
import hibrnate.clients.CouponClientFacade;
import hibrnate.clients.Customerfacade;

public class CouponSystem {
	private static CouponSystem couponSystem = null;
	private AdminFacade admin = new AdminFacade();
	private Customerfacade customer = new Customerfacade();
	private CompanyFacade company = new CompanyFacade();
	private dailyCouponExpriationTask task = new dailyCouponExpriationTask();
	private CouponSystem() {
		//task.run();
	}

	public static CouponSystem getInstace() {
		if (couponSystem == null) {
			synchronized (CouponSystem.class) {
				if (couponSystem == null)
					couponSystem = new CouponSystem();
			}
		}
		System.out.println("coupon system start");
		return couponSystem;
	}

	public CouponClientFacade login(String name, String password, ClientType type) {
		switch (type) {
		case ADMIN:
			return admin.login(name, password, type);
		case COMPANY:
			return company.login(name, password, type);
		case CUSTOMER:
			return customer.login(name, password, type);
		default:
			return null;
		}

	}

	public void shutDown() {
		task.stopTask();
	}
}
