package com.avnrsol.eventfactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.service.interfaces.IVendorService;

@Controller
@RequestMapping(value= "/dash/vendor")
public class VendorController {
	
//	@Autowired
	private IVendorService vendorService;

	
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("vendor", new Vendor()); 
	   
		modelAndView.setViewName("dash/vendor/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewVendor(@ModelAttribute Vendor vendor) {
		ModelAndView modelAndView = new ModelAndView();
		
		vendorService.add(vendor);
//		Vendor v = vrepo.findVendorById((long) vv.getId());
//		if(v !=null) {
//			modelAndView.addObject("message", "Vendor " + v.getName() +" has been registered successfully");
//		}else {
//			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
//		}
		
		return modelAndView;
	}
	
}