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
import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;
import com.avnrsol.eventfactory.service.interfaces.IVendorService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value= "/dash/vendor")
public class VendorController {
	
	

		@Autowired
		ImageService imageService;
		
    
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
	public ModelAndView createNewVendor(@ModelAttribute Vendor vendor, @RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(vendor == null) {
			System.out.println("vendor null");
		}else {
			
		}
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		files.add(file);
		try {
			List<Image> i = imageService.saveUploadedFiles(files);
			
			vendor.setImages(i);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vendor v = vendorService.add(vendor);
		
		
		if(v !=null) {
			modelAndView.addObject("message", "Vendor " + v.getName() +" has been registered successfully");
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
		Page<Vendor> vendors = vendorService.findAllVendor(0);
		
		modelAndView.setViewName("dash/vendor/viewAll");
		
	//	modelAndView.addObject("vendors", vendors.getContent());
		modelAndView.addObject("title", "Vendor Information > All Vendors");
		
		
		
        int evalPageSize = pageSize.orElse(Constants.INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? Constants.INITIAL_PAGE : page.get() - 1;
       
        Page<Vendor> vendorlist = vendorRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(vendorlist.getTotalPages(),vendorlist.getNumber(),Constants.BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",vendorlist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", Constants.PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dash/vendor/viewAll");
      
        modelAndView.addObject("pager", pager);
        
		return modelAndView;
	}

	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editEntityView(@RequestParam("id") Long id){
		ModelAndView modelAndView = new ModelAndView();
		Vendor vendor = vendorService.findById(id);
		modelAndView.setViewName("dash/vendor/edit");
		//modelAndView.addObject("vendor", new Vendor());
		modelAndView.addObject("title", "Vendor Information > Edit "+ vendor.getName());
		modelAndView.addObject("vendor",  vendor);

		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView updateEntity( Vendor vendor, @RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();

		if(vendor == null) {
			System.out.println("vendor null");
		}else {

		}

		if(file != null){
			List<MultipartFile> files = new ArrayList<MultipartFile>();
			files.add(file);
			try {
				List<Image> i = imageService.saveUploadedFiles(files);

				vendor.setImages(i);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		Vendor v2 = vendorService.add(vendor);


		if(vendor !=null) {
			modelAndView.addObject("message", "Vendor " + v2.getName() +" has been updated successfully");
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

		vendorService.delete(vendorService.findById(id));

//			modelAndView.addObject("message", "Vendor Deleted!");
//			modelAndView.addObject("m",  0);

		modelAndView.setViewName("redirect:/dash/vendor/viewAll");
		redir.addFlashAttribute("message","Vendor Deleted!");
		redir.addFlashAttribute("m","0");
		return modelAndView;
	}

}
