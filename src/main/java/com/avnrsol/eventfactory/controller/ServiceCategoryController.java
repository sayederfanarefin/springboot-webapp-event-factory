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
import com.avnrsol.eventfactory.Model.ServiceCategory;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;
import com.avnrsol.eventfactory.service.interfaces.IServiceCategoryService;

@Controller
@RequestMapping(value= "/dash/serviceCategory")
public class ServiceCategoryController {
	
	@Autowired
	ImageService imageService;
    
    
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
	public ModelAndView createNewServiceCategory(@ModelAttribute ServiceCategory serviceCategory, @RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(serviceCategory == null) {
			System.out.println("serviceCategory null");
		}else {
			
		}
		
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		files.add(file);
		try {
			List<Image> i = imageService.saveUploadedFiles(files);
			
			serviceCategory.setImages(i);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		ServiceCategory v = serviceCategoryService.add(serviceCategory);
		if(v !=null) {
			modelAndView.addObject("message", "ServiceCategory " + v.getName() +" has been registered successfully");
			modelAndView.addObject("m",  0);
		}else {
			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
			modelAndView.addObject("m",  1);
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
		
		
		
        int evalPageSize = pageSize.orElse(Constants.INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? Constants.INITIAL_PAGE : page.get() - 1;
       
        Page<ServiceCategory> serviceCategorylist = serviceCategoryRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(serviceCategorylist.getTotalPages(),serviceCategorylist.getNumber(),Constants.BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",serviceCategorylist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", Constants.PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dash/serviceCategory/viewAll");
      
        modelAndView.addObject("pager", pager);
        
        
        
		return modelAndView;
	}
	
}
