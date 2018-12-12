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
import com.avnrsol.eventfactory.Model.Order;
import com.avnrsol.eventfactory.Repository.OrderRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.ImageService;
import com.avnrsol.eventfactory.service.interfaces.IOrderService;

@Controller
@RequestMapping(value= "/dashboard/order")
public class AdminOrderController {
	
	@Autowired
	ImageService imageService;
    
    
	@Autowired
	private IOrderService OrderService;

	
	@Autowired
	private OrderRepository OrderRepository;

	@RequestMapping(value="/viewAll", method = RequestMethod.GET)
	public ModelAndView viewAll(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page){
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Page<Order> orders = OrderService.findAllOrder(0);
		
		modelAndView.setViewName("dash/order/viewAll");
		
	//	modelAndView.addObject("Orders", Orders.getContent());
		modelAndView.addObject("title", "Order Information > All Orders");
		
		
		
        int evalPageSize = pageSize.orElse(Constants.INITIAL_PAGE_SIZE);
       
        int evalPage = (page.orElse(0) < 1) ? Constants.INITIAL_PAGE : page.get() - 1;
       
        Page<Order> Orderlist = OrderRepository.findAll(new PageRequest(evalPage, evalPageSize));
    
        PagerModel pager = new PagerModel(Orderlist.getTotalPages(),Orderlist.getNumber(),Constants.BUTTONS_TO_SHOW);
       
        modelAndView.addObject("clientlist",Orderlist);
       
        modelAndView.addObject("selectedPageSize", evalPageSize);
        
        modelAndView.addObject("pageSizes", Constants.PAGE_SIZES);
        modelAndView.addObject("baseUrl", "/dashboard/order/viewAll");
      
        modelAndView.addObject("pager", pager);
        
        
        
		return modelAndView;
	}
	
}
