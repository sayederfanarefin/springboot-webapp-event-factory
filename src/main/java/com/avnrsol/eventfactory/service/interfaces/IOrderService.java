package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Order;

public interface IOrderService {
	Order findById(Long id);
    void delete(Order order);
    Order updateOrder(Order order);
    
    Page<Order> findAllOrder( int page);
	Order add(Order order);
    
}
