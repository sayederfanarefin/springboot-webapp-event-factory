package com.avnrsol.eventfactory.controller;

import com.avnrsol.eventfactory.Model.*;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;
import com.avnrsol.eventfactory.service.interfaces.IServiceoService;
import com.avnrsol.eventfactory.service.interfaces.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value= "/livesearch")
public class SearchController {

	@Autowired
	private IServiceoService serviceoService;

	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	private VendorRepository vendorRepository;


	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView registration( SearchCriteria searchCriteria, Errors errors){

		System.out.println("searching.............................");




		List<Serviceo> services = serviceoService.search(searchCriteria.getUsername());


		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("searchCriteria", new SearchCriteria() );


		modelAndView.setViewName("search-results");
		modelAndView.addObject("category", services);
		modelAndView.addObject("categories", serviceCategoryRepository.findAll());
		modelAndView.addObject("vendors", vendorRepository.findAll());
		return modelAndView;
	}
}

