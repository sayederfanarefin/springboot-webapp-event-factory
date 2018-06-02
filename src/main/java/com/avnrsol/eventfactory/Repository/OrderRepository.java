package com.avnrsol.eventfactory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Order findById(Long id);
	Order save(Order order);
}