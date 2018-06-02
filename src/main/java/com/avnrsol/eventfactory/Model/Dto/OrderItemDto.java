package com.avnrsol.eventfactory.Model.Dto;

import com.avnrsol.eventfactory.Model.Serviceo;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class OrderItemDto {

	private Long id;
	
	
	@JsonBackReference(value="order-order-item")
	private OrderDto order;
	
	
	public Long quantity;
	
	public double amount;
	
	@JsonBackReference(value="service-order-item")
	public ServiceoDto service;
	    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}

	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ServiceoDto getService() {
		return service;
	}

	public void setService(ServiceoDto service) {
		this.service = service;
	}

	
	
	
	
}