package spring.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Rest_Controller {
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value ="/login",method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("myLogin");
	}
	
//	@RequestMapping(value ="/login",method = RequestMethod.POST)
//	public String doSomething (@RequestBody LoginBean login){
//		return login.getPassword()+","+login.getUserName();
//	}
	
//	@RequestMapping("/error")
//	public  String error(){
//		return "ERROR";
//	}
	
	@RequestMapping("role")
	public boolean getRule(){
		return request.isUserInRole("COMPANY");
	}
	@RequestMapping("role2")
	public String getRule2(){
		return request.getUserPrincipal().getName();
	}
	@RequestMapping("role3")
	public Collection<GrantedAuthority> getRule3(){
		return (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	
	@RequestMapping("/lala")
	public List<String> lala(){
		List<String> list = new ArrayList<String>();
		list.add("guy");
		list.add("lala");
		return list;
				
				
	}
}
