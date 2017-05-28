package com.sudha.domains;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping(value="/home/")
public class LoginController {

	@RequestMapping(method=RequestMethod.GET, value="/login"  )
	public ModelAndView logn(){
		System.out.println("HI LOGIN");
		ModelAndView model = new ModelAndView("login");
		model.addObject("command", new Login());
		
		return model;
	}
	
	@RequestMapping(value="/loginrequest", method=RequestMethod.POST)
	public ModelAndView verifyLogin(@ModelAttribute("test") Login login ){
		
		System.out.println("!!!!!!HI verify LOGIN");
		
		ModelAndView model = new ModelAndView("loginResponse");
		model.addObject(login);
		return model;
	}
	

	
}
