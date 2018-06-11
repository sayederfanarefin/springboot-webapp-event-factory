package com.avnrsol.eventfactory.controller;

import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avnrsol.eventfactory.Model.Order;
import com.avnrsol.eventfactory.Model.OrderItem;
import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.Model.ajaxTest;
import com.avnrsol.eventfactory.Model.Dto.OrderDto;
import com.avnrsol.eventfactory.Model.Dto.OrderItemDto;
import com.avnrsol.eventfactory.Repository.OrderRepository;
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

	@Autowired
	private OrderRepository orderRepository;

	// @Autowired
	// private ModelMapper modelMapper;
	//
	@PostMapping("/ads")
	public boolean createNewOrder(@RequestBody OrderDto orderDto, Errors errors) {
		if (errors.hasErrors()) {

			System.out.println(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}

		System.out.println(orderDto.getNote());
		// System.out.println(data);

		// modelMapper.map(orderDto, Order.class);
		User u = userService.findUserByEmail(orderDto.getUser().getEmail());
		u.setAddress(orderDto.getUser().getAddress());
		u.setFirstName(orderDto.getUser().getFirstName());
		u.setLastName(orderDto.getUser().getLastName());
		u.setPhone(orderDto.getUser().getPhone());
		u.setCompanyName(orderDto.getUser().getCompanyName());

		userService.saveRegisteredUser(u);

		Order o = new Order();
		o.setCuponCode(orderDto.getCuponCode());
		o.setNote(orderDto.getNote());
		o.setPaymentType(orderDto.getPaymentType());
		o.setSubTotal(orderDto.getSubTotal());
		o.setTotal(orderDto.getTotal());
		o.setUser(u);
		o.setStartDate(new Date(orderDto.getStartDate()));
		o.setEndDate(new Date(orderDto.getEndDate()));

		//System.out.println("->->->->->->->->->->->->->->->->->->->->->" + orderDto.getOrderItem().size());
		for (int i = 0; i < orderDto.getOrderItem().size(); i++) {
			OrderItemDto oidto = orderDto.getOrderItem().get(i);
			OrderItem oi = new OrderItem();
			oi.setAmount(oidto.getAmount());
			oi.setQuantity(oidto.getQuantity());
			oi.setOrder(o);
			oi.setService(serviceoRepository.findServiceoById(oidto.getService().getId()));
			o.addOrderItem(oi);
			//System.out.println("->->->->->->->->->->->->->");
		}

		Order oo = orderRepository.save(o);
		if(oo==null) {
			return false;
		}else {
			return true;
		}
		
	}

	@PostMapping("/testAjax")
	public String createNewOrder1(@RequestBody ajaxTest a, Errors errors) {
		if (errors.hasErrors()) {

			System.out.println(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
		}

		System.out.println(a.getName());
		// System.out.println(data);
		return "got it";
	}
}
