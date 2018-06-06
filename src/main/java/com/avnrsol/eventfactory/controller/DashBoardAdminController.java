package com.avnrsol.eventfactory.controller;

import com.avnrsol.eventfactory.Model.Privilege;
import com.avnrsol.eventfactory.Model.Role;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.configuration.PrivilegeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.service.UserService;

import java.security.Principal;
import java.util.Iterator;

@Controller
@RequestMapping(value= "/dash/admin")
public class DashBoardAdminController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value= {"/", "home"})
	public ModelAndView adminHome(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		if(hasPrivilege(userService.findUserByEmail(principal.getName()))){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());


			System.out.println("->->->->->->->->->->->->->->->-> admin dash board");
			modelAndView.addObject("title", "Dashboard");
			modelAndView.setViewName("dash/admindash");

			return modelAndView;

		}else{
			return new ModelAndView("redirect:/");
		}
		

	}

	private boolean hasPrivilege( User user) {

		boolean flag = false;

		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			if(r.getName().equals(Constants.ADMIN) || r.getName().equals(Constants.SUPER_ADMIN)){
				flag = true;
			}
//			Iterator<Privilege> privileges = r.getPrivileges().iterator();
//			while (privileges.hasNext()) {
//				Privilege privilege1 = privileges.next();
//				if (privilege1.getName().equals(privilege)) {
//					flag = true;
//					break;
//				}
//			}
		}
		return flag;

	}
		
}
