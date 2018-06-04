package com.avnrsol.eventfactory.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.avnrsol.eventfactory.Model.Role;
import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.Repository.RoleRepository;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;


	@RequestMapping(value= "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("logIn2");
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("logIn2");
		} else {
			
			Role customerRole = roleRepository.findByName(Constants.USER);
			Collection<Role> roles = new ArrayList<Role>(Arrays.asList(customerRole));
			user.setRoles(roles);
			userService.registerNewUserAccount(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("logIn2");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public RedirectView home(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		String sb = "";
		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			sb = r.getName();
		}
		
		System.out.println("->->->->->->->->->->->->->->->-> redirect method");
		
		if(sb.equals(Constants.SUPER_ADMIN)) {
			return new RedirectView("/dash/superAdmin/");
		}else if(sb.equals(Constants.ADMIN)) {
			return new RedirectView("/dash/admin/");
		}else {
			return new RedirectView("/dash/customer/");
		}
		
	}
	
}
