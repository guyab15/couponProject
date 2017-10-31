package hibernate.exption;

public class CouponProjectException extends Exception {

	private static final long serialVersionUID = 4275616224203427214L;

	public CouponProjectException(String message) {
		super(message);
	}

	public static class CouponException extends CouponProjectException {
		
		private static final long serialVersionUID = 1359599464071283754L;

		

		public CouponException(String message) {
			super(message);
		}

	}

	public static class CompanyException extends CouponProjectException {

		private static final long serialVersionUID = 6079555337777161665L;

		public CompanyException(String message) {
			super(message);
		}

	}

	public static class CustomerException extends CouponProjectException {

		private static final long serialVersionUID = -2244673097030189035L;

		public CustomerException(String message) {
			super(message);
		}

	}

	public static class LoginException extends CouponProjectException {

		private static final long serialVersionUID = 3838582977539893681L;

		public LoginException(String message) {
			super(message);
		}

	}

	public static class AccessForbiddenException extends LoginException {

		private static final long serialVersionUID = -4001241213709561032L;

		public AccessForbiddenException(String message) {
			super(message);
		}

	}

	public static class AdminLoginException extends LoginException {

		private static final long serialVersionUID = -9174025152345459372L;

		public AdminLoginException(String message) {
			super(message);
		}

	}

	public static class CompanyLoginException extends LoginException {

		private static final long serialVersionUID = 2297521675763391904L;

		public CompanyLoginException(String message) {
			super(message);
		}

	}

	public static class CustomerLoginException extends LoginException {

		private static final long serialVersionUID = 6794083561585382802L;

		public CustomerLoginException(String message) {
			super(message);
		}

	}

}
