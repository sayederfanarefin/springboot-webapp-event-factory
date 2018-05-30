package com.avnrsol.eventfactory.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

@Entity

public class OrderItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public Long quantity;
	
	public double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_item_service")
	public Serviceo service;

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_item")
	public Order order;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public Serviceo getService() {
		return service;
	}



	public void setService(Serviceo service) {
		this.service = service;
	}



	public OrderItem(Long quantity, double amount, Serviceo service) {
		super();
		this.quantity = quantity;
		this.amount = amount;
		this.service = service;
	}



	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}

	
	
}
