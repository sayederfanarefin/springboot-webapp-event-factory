package com.avnrsol.eventfactory.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.avnrsol.eventfactory.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;
import com.avnrsol.eventfactory.service.interfaces.IServiceoService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value= "/dash/serviceo")
public class AdminServiceController {
	
    
    
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
		
		try {
			Image image = imageService.saveUploadedFile(file);
			serviceo.addImage(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Serviceo v = serviceoService.add(serviceo);
		if(v !=null) {
			
			System.out.println("size images after add "+v.getImages().size() + " ||| " + v.getImages().get(0).getUrl());
			
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


	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editEntityView(@RequestParam("id") Long id){
		ModelAndView modelAndView = new ModelAndView();
		Serviceo service = serviceoService.findById(id);
		modelAndView.setViewName("dash/serviceo/edit");

		modelAndView.addObject("title", "Service Information > Edit "+ service.getName());
		modelAndView.addObject("service",  service);
		modelAndView.addObject("categories", serviceCategoryRepository.findAll());
		modelAndView.addObject("vendors", vendorRepository.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView updateEntity( Serviceo service, @RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.setViewName("redirect:/dash/serviceo/viewAll");

		modelAndView.setViewName("dash/serviceo/edit");

		modelAndView.addObject("title", "Service Information > Edit "+ service.getName());

		modelAndView.addObject("categories", serviceCategoryRepository.findAll());
		modelAndView.addObject("vendors", vendorRepository.findAll());

		if(service == null) {
			System.out.println("service null");
		}else {

		}

		if(file != null){
			List<MultipartFile> files = new ArrayList<MultipartFile>();
			files.add(file);
			try {
				List<Image> i = imageService.saveUploadedFiles(files);

				service.setImages(i);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		Serviceo v2 = serviceoService.updateServiceo(service);

		modelAndView.addObject("service",  v2);
		if(service !=null) {
			modelAndView.addObject("message", "Service " + v2.getName() +" has been updated successfully");
			modelAndView.addObject("m",  0);
		}else {
			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
			modelAndView.addObject("m",  1);
		}


		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView  deleteEntity(@RequestParam("id") Long id , RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();

		serviceoService.delete(serviceoService.findById(id));

		modelAndView.setViewName("redirect:/dash/serviceo/viewAll");

		redir.addFlashAttribute("message","Service Deleted!");
		redir.addFlashAttribute("m","0");
		return modelAndView;
	}


	
}
