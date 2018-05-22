package com.avnrsol.eventfactory.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.service.ServiceCategoryService;
import com.avnrsol.eventfactory.service.ServiceoService;
import com.avnrsol.eventfactory.service.UserService;
import com.avnrsol.eventfactory.service.VendorService;

@Controller
public class StoreController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServiceoRepository serviceoRepository;
	
	
	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		 modelAndView.addObject("clientlist", serviceCategoryRepository.findAll());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/service", method = RequestMethod.GET)
	public ModelAndView service(@RequestParam("id") Long id){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("single-product-sidebar");
		modelAndView.addObject("serv", serviceoRepository.findServiceoById(id));
		return modelAndView;
	}
	
		
}
