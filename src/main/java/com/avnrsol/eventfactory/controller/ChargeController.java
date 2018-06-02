package com.avnrsol.eventfactory.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Model.Image;
import com.avnrsol.eventfactory.Model.PagerModel;
import com.avnrsol.eventfactory.Model.Charge;
import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ChargeRepository;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;

@Controller
@RequestMapping(value= "/dash/charge")
public class ChargeController {
	
	
	@Autowired
	private ChargeRepository chargeRepository;
	
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("charge", new Charge()); 
		modelAndView.addObject("title", "Charge Information > Add Charge");
		
		modelAndView.setViewName("dash/charge/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewCharge(@ModelAttribute Charge charge) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(charge == null) {
			System.out.println("charge null");
		}else {
			
		}
		
		Charge v = chargeRepository.save(charge);
		if(v !=null) {
			
			
			
			modelAndView.addObject("message", "Services has been registered successfully");
			modelAndView.addObject("m",  0);
		}else {
			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
			modelAndView.addObject("m",  1);
		}
		
		
		return modelAndView;
	}
	
	
//	@RequestMapping(value="/viewAll", method = RequestMethod.GET)
//	public ModelAndView viewAll(@RequestParam("pageSize") Optional<Integer> pageSize,
//            @RequestParam("page") Optional<Integer> page){
//		
//		
//		
//		ModelAndView modelAndView = new ModelAndView();
//		Page<Charge> charges = chargeService.findAllCharge(0);
//		
//		modelAndView.setViewName("dash/charge/viewAll");
//		
//	//	modelAndView.addObject("charges", charges.getContent());
//		modelAndView.addObject("title", "Service Information > All Services");
//		
//		
//		
//        int evalPageSize = pageSize.orElse(Constants.INITIAL_PAGE_SIZE);
//       
//        int evalPage = (page.orElse(0) < 1) ? Constants.INITIAL_PAGE : page.get() - 1;
//       
//        Page<Charge> chargelist = chargeRepository.findAll(new PageRequest(evalPage, evalPageSize));
//    
//        PagerModel pager = new PagerModel(chargelist.getTotalPages(),chargelist.getNumber(),Constants.BUTTONS_TO_SHOW);
//       
//        modelAndView.addObject("clientlist",chargelist);
//       
//        modelAndView.addObject("selectedPageSize", evalPageSize);
//        
//        modelAndView.addObject("pageSizes", Constants.PAGE_SIZES);
//        modelAndView.addObject("baseUrl", "/dash/charge/viewAll");
//      
//        modelAndView.addObject("pager", pager);
//        
//        
//        
//		return modelAndView;
//	}
	
}
