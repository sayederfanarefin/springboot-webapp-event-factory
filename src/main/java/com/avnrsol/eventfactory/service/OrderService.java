package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.Order;
import com.avnrsol.eventfactory.Repository.OrderRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.interfaces.IOrderService;

@Service
@Transactional
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public void delete(Order order) {
		orderRepository.delete(order);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Page<Order> findAllOrder(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return orderRepository.findAll(request);
	}

	@Override
	public Order add(Order order) {
		
		return orderRepository.save(order);
	}

	
}