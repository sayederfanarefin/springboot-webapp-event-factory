package com.avnrsol.eventfactory.Model.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public class OrderDto {

	private Long id;

	public String startDate;

	public String endDate;

	public String note;
	
	public Long subTotal;
	
	public String cuponCode;
	
	public Long total;
	
	public String paymentType;

	public Long serviceCharge;

	public Long deliveryCharge;
	
	@JsonBackReference(value="order-user")
	private UserDto user;
	

	@JsonManagedReference(value="order-order-item")
	private List<OrderItemDto> orderItem  = new ArrayList<OrderItemDto>();
	
	
	public Date createdAt;
	


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.000 ", timezone = "UTC")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}

	public String getCuponCode() {
		return cuponCode;
	}

	public void setCuponCode(String cuponCode) {
		this.cuponCode = cuponCode;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<OrderItemDto> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemDto> orderItem) {
		this.orderItem = orderItem;
	}


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Long serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Long getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Long deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
}