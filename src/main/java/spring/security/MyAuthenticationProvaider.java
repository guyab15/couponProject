package spring.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import spring.rest.CompanyRestController;
import spring.rest.CustomerRestController;

@Component
public class MyAuthenticationProvaider extends AbstractUserDetailsAuthenticationProvider {

	Logger logger = Logger.getLogger(MyAuthenticationProvaider.class);

	@Autowired
	TypeUserDetailes typeUserDetails;
	@Autowired
	CompanyRestController companyRestController;
	@Autowired
	CustomerRestController customerRestController;
	@Autowired
	private HttpServletRequest request;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		logger.error("guy    " + token.getPrincipal());
		logger.error("guy    " + token.getName());
		logger.error("guy    " + token.getAuthorities());
		logger.error("guy    " + token.getDetails());
		logger.error("guy    " + token.getCredentials());
		logger.error("guy    " + request.getParameter("type") + "  wow");

		if (userDetails == null) {
			throw new BadCredentialsException("the password no coorect");
		}
		if (!userDetails.getPassword().equals(token.getCredentials())) {
			throw new BadCredentialsException("the password no coorect");
		}
		if (request.getParameter("type").equals("company")) {
			companyRestController.setPass(token.getCredentials().toString());
			companyRestController.setUserName(token.getName());
			companyRestController.init();
		}
		if (request.getParameter("type").equals("customer")) {
			customerRestController.setPass(token.getCredentials().toString());
			customerRestController.setUserName(token.getName());
			customerRestController.init();
		}
	}

	@Override
	protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken auth)
			throws AuthenticationException {

		UserDetails userDetails = typeUserDetails.loadUserByUsername(userName);
		return userDetails;
	}

}
