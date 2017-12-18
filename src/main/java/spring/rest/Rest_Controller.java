package spring.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Rest_Controller {
	
	@RequestMapping(value ="/login",method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("myLogin");
	}

	
	
}
