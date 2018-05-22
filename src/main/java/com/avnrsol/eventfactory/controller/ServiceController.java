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
import com.avnrsol.eventfactory.Model.Serviceo;
import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;
import com.avnrsol.eventfactory.service.interfaces.IServiceoService;

@Controller
@RequestMapping(value= "/dash/serviceo")
public class ServiceController {
	
    
    
	@Autowired
	private IServiceoService serviceoService;

	@Autowired
	ImageService imageService;
	
	
	@Autowired
	private ServiceoRepository serviceoRepository;
	
	
	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("serviceo", new Serviceo()); 
		modelAndView.addObject("title", "Service Information > Add Service");
		modelAndView.addObject("vendors", vendorRepository.findAll());
		modelAndView.addObject("categories", serviceCategoryRepository.findAll());
		modelAndView.setViewName("dash/serviceo/add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewServiceo(@ModelAttribute Serviceo serviceo, @RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(serviceo == null) {
			System.out.println("serviceo null");
		}else {
			
		}
		
		Vendor vv = serviceo.getVendor();
		
	
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		files.add(file);
		try {
			List<Image> i = imageService.saveUploadedFiles(files);
			
			serviceo.setImages(i);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Serviceo v = serviceoService.add(serviceo);
		if(v !=null) {
			modelAndView.addObject("message", "Service " + v.getName() +" has been registered successfully");
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
		Page<Serviceo> serviceos = serviceoService.findAllServiceo(0);
		
		modelAndView.setViewName("dash/serviceo/viewAll");
		
	//	modelAndView.addObject("serviceos", serviceos.getContent());
		modelAndView.addObject("title", "Service Information > All Services");
		
		
		
        int evalPageSize = pageSize.orElse(Constants.INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? Constants.INITIAL_PAGE : page.get() - 1;
       
        Page<Serviceo> serviceolist = serviceoRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(serviceolist.getTotalPages(),serviceolist.getNumber(),Constants.BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",serviceolist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", Constants.PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dash/serviceo/viewAll");
      
        modelAndView.addObject("pager", pager);
        
        
        
		return modelAndView;
	}
	
}
