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
import com.avnrsol.eventfactory.Model.Serviceo;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.service.interfaces.IServiceoService;

@Controller
@RequestMapping(value= "/dash/serviceo")
public class ServiceController {
	
	
	
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 2;
    private static final int[] PAGE_SIZES = { 2, 4};
    
    
    
	@Autowired
	private IServiceoService serviceoService;

	
	@Autowired
	private ServiceoRepository serviceoRepository;
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("serviceo", new Serviceo()); 
		modelAndView.addObject("title", "Serviceo Information > Add Serviceo");
		modelAndView.setViewName("dash/serviceo/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewServiceo(@ModelAttribute Serviceo serviceo) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(serviceo == null) {
			System.out.println("serviceo null");
		}else {
			
		}

		Serviceo v = serviceoService.add(serviceo);
		if(v !=null) {
			modelAndView.addObject("message", "Serviceo " + v.getName() +" has been registered successfully");
		}else {
			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
		}
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/viewAll", method = RequestMethod.GET)
	public ModelAndView viewAll(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page){
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Page<Serviceo> serviceos = serviceoService.findAllServiceo(0);
		
		modelAndView.setViewName("dash/serviceo/viewAll");
		
	//	modelAndView.addObject("serviceos", serviceos.getContent());
		modelAndView.addObject("title", "Serviceo Information > All Serviceos");
		
		
		
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
       
        Page<Serviceo> serviceolist = serviceoRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(serviceolist.getTotalPages(),serviceolist.getNumber(),BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",serviceolist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dash/serviceo/viewAll");
      
        modelAndView.addObject("pager", pager);
        
        
        
		return modelAndView;
	}
	
}
