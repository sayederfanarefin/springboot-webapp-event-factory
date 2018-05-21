package com.avnrsol.eventfactory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.service.interfaces.IVendorService;

@Controller
@RequestMapping(value= "/dash/vendor")
public class VendorController {
	
	@Autowired
	private IVendorService vendorService;

	
	@Autowired
	private VendorRepository vendorRepository;
	
	
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
		
		if(vendor == null) {
			System.out.println("vendor null");
		}else {
			System.out.println("vendor not null");
			System.out.println("address" + vendor.getAddress());
			System.out.println("name" + vendor.getName());
			System.out.println("phone" + vendor.getPhone());
			System.out.println("country" + vendor.getCountry());
			System.out.println("city" + vendor.getCity());
			System.out.println("zip" + vendor.getZip());
			System.out.println(vendor.getEmail());
			System.out.println(vendor.getDescription());
		}

		Vendor v = vendorService.add(vendor);
		if(v !=null) {
			modelAndView.addObject("message", "Vendor " + v.getName() +" has been registered successfully");
		}else {
			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/viewAll", method = RequestMethod.GET)
	public ModelAndView viewAll(@RequestParam(value="pageId", required = false, defaultValue = "0") int pageId){
		ModelAndView modelAndView = new ModelAndView();
		Page<Vendor> vendors = vendorService.findAllVendor(pageId);
		
		modelAndView.setViewName("dash/vendor/viewAll");
		modelAndView.addObject("vendors", vendors.getContent());
		
		return modelAndView;
	}
	
}
