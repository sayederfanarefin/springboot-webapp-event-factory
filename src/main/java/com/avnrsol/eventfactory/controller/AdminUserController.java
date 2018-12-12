package com.avnrsol.eventfactory.controller;

import com.avnrsol.eventfactory.Model.Charge;
import com.avnrsol.eventfactory.Model.Image;
import com.avnrsol.eventfactory.Model.Serviceo;
import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value= "/dashboard/my-account")
public class AdminUserController {
	
	
	@Autowired
	private UsersRepository usersRepository;

//	@RequestMapping(value="/edit", method = RequestMethod.GET)
//	public ModelAndView editEntityView(@RequestParam("id") Long id){
//		ModelAndView modelAndView = new ModelAndView();
//		Serviceo service = serviceoService.findById(id);
//		modelAndView.setViewName("dash/serviceo/edit");
//
//		modelAndView.addObject("title", "Service Information > Edit "+ service.getName());
//		modelAndView.addObject("service",  service);
//		modelAndView.addObject("categories", serviceCategoryRepository.findAll());
//		modelAndView.addObject("vendors", vendorRepository.findAll());
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	public ModelAndView updateEntity( Serviceo service, @RequestParam("file") MultipartFile file) {
//		ModelAndView modelAndView = new ModelAndView();
//		//modelAndView.setViewName("redirect:/dashboard/serviceo/viewAll");
//
//		modelAndView.setViewName("dash/serviceo/edit");
//
//		modelAndView.addObject("title", "Service Information > Edit "+ service.getName());
//
//		modelAndView.addObject("categories", serviceCategoryRepository.findAll());
//		modelAndView.addObject("vendors", vendorRepository.findAll());
//
//		if(service == null) {
//			System.out.println("service null");
//		}else {
//
//		}
//
//		if(file != null){
//			List<MultipartFile> files = new ArrayList<MultipartFile>();
//			files.add(file);
//			try {
//				List<Image> i = imageService.saveUploadedFiles(files);
//
//				service.setImages(i);
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//
//		Serviceo v2 = serviceoService.updateServiceo(service);
//
//		modelAndView.addObject("service",  v2);
//		if(service !=null) {
//			modelAndView.addObject("message", "Service " + v2.getName() +" has been updated successfully");
//			modelAndView.addObject("m",  0);
//		}else {
//			modelAndView.addObject("message", "Some thing went wrong. Please try again later.");
//			modelAndView.addObject("m",  1);
//		}
//
//
//		return modelAndView;
//	}
//



	@RequestMapping(value="/view", method = RequestMethod.GET)
	public ModelAndView view(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dash/user");
		modelAndView.addObject("title", usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getFirstName() + " " + usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getLastName());
		modelAndView.addObject("user", usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		modelAndView.addObject("userdob",usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getDob().toString());
		return modelAndView;
	}
	
}
