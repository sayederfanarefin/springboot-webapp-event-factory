package com.avnrsol.eventfactory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.service.UserService;

@Controller
@RequestMapping(value= "/dash/admin")
public class DashBoardController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value= {"/", "home"})
	public ModelAndView adminHome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		System.out.println("->->->->->->->->->->->->->->->-> admin dash board");
		
		modelAndView.setViewName("dash/admindash");
		
		return modelAndView;
	}
	
		
}
