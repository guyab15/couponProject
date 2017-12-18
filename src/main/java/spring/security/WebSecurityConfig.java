package spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	
	@Autowired
	private MyAuthenticationProvaider myAuthenticationProvaider;
	
	 @Autowired
	  private  CustomSuccessHandler customSuccessHandler;
	 
	
	 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		 http
	      .authorizeRequests()
	        .antMatchers("/company/**").hasRole("COMPANY")
	        .antMatchers("/customer/**").hasRole("CUSTOMER")
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .anyRequest().permitAll()
	        .and()
	    .formLogin()  
	        .loginPage("/login").successHandler(customSuccessHandler); 
//	        .permitAll() ;
		 
		http
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		http
		.csrf().disable();
		
    }
	
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
//    	auth
//            .inMemoryAuthentication()
//                .withUser("a").password("a").roles("COMPANY");
    	auth
      .authenticationProvider(myAuthenticationProvaider);
   
    }
    
    
}
