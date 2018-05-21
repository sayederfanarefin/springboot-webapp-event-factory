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
import com.avnrsol.eventfactory.Model.ServiceCategory;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.service.interfaces.IServiceCategoryService;

@Controller
@RequestMapping(value= "/dash/serviceCategory")
public class ServiceCategoryController {
	
	
	
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 2;
    private static final int[] PAGE_SIZES = { 2, 4};
    
    
    
	@Autowired
	private IServiceCategoryService serviceCategoryService;

	
	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("serviceCategory", new ServiceCategory()); 
		modelAndView.addObject("title", "Category Information > Add Category");
		modelAndView.setViewName("dash/serviceCategory/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewServiceCategory(@ModelAttribute ServiceCategory serviceCategory) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(serviceCategory == null) {
			System.out.println("serviceCategory null");
		}else {
			
		}

		ServiceCategory v = serviceCategoryService.add(serviceCategory);
		if(v !=null) {
			modelAndView.addObject("message", "ServiceCategory " + v.getName() +" has been registered successfully");
		}else {
			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
		}
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/viewAll", method = RequestMethod.GET)
	public ModelAndView viewAll(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page){
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Page<ServiceCategory> serviceCategorys = serviceCategoryService.findAllServiceCategory(0);
		
		modelAndView.setViewName("dash/serviceCategory/viewAll");
		
	//	modelAndView.addObject("serviceCategorys", serviceCategorys.getContent());
		modelAndView.addObject("title", "Category Information > All Categories");
		
		
		
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
       
        Page<ServiceCategory> serviceCategorylist = serviceCategoryRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(serviceCategorylist.getTotalPages(),serviceCategorylist.getNumber(),BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",serviceCategorylist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dash/serviceCategory/viewAll");
      
        modelAndView.addObject("pager", pager);
        
        
        
		return modelAndView;
	}
	
}
