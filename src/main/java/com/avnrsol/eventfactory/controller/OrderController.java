package com.avnrsol.eventfactory.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avnrsol.eventfactory.Model.Order;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.service.UserService;
import com.avnrsol.eventfactory.service.VendorService;

@RestController
public class OrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceoRepository serviceoRepository;

	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	private VendorService vendorService;

	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST , consumes = "application/json")
	
//	@PostMapping("/placeOrder")
	public String createNewOrder( @Valid @RequestBody Order order, Errors errors) {
		 if (errors.hasErrors()) {
		
		
		System.out.println(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
		 }
		//System.out.println(order.getNote());
		//System.out.println(data);
		return "got it";
	}
}
