package com.avnrsol.eventfactory.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.PagerModel;
import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.service.interfaces.IVendorService;

@Controller
@RequestMapping(value= "/dash/vendor")
public class VendorController {
	
	
	
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 2;
    private static final int[] PAGE_SIZES = { 2, 4};
    
    
    
	@Autowired
	private IVendorService vendorService;

	
	@Autowired
	private VendorRepository vendorRepository;
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("vendor", new Vendor()); 
		modelAndView.addObject("title", "Vendor Information > Add Vendor");
		modelAndView.setViewName("dash/vendor/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewVendor(@ModelAttribute Vendor vendor) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(vendor == null) {
			System.out.println("vendor null");
		}else {
			
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
	public ModelAndView viewAll(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page){
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Page<Vendor> vendors = vendorService.findAllVendor(0);
		
		modelAndView.setViewName("dash/vendor/viewAll");
		
	//	modelAndView.addObject("vendors", vendors.getContent());
		modelAndView.addObject("title", "Vendor Information > All Vendors");
		
		
		
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
       
        Page<Vendor> vendorlist = vendorRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(vendorlist.getTotalPages(),vendorlist.getNumber(),BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",vendorlist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dash/vendor/viewAll");
      
        modelAndView.addObject("pager", pager);
        
        
        
		return modelAndView;
	}
	
}
