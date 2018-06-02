package com.avnrsol.eventfactory.Model.Dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public class ServiceoDto {


	private Long id;

	
	
	private String name;
	
	private double price;
	
	private double discount;
	
	@JsonManagedReference(value="service-order-item")
	public List<OrderItemDto> orderItems;
	

	private String description;

	
	private boolean available;

	
	private List<ImageDto> images = new ArrayList<ImageDto>();

	@JsonBackReference(value="service-category-servie")
	private ServiceCategoryDto serviceCategory;

	@JsonBackReference(value="service-vendor")
	private VendorDto vendor;

	public ServiceoDto() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public List<ImageDto> getImages() {
		return images;
	}

	public void setImages(List<ImageDto> images) {
		this.images = images;
	}

	public ServiceCategoryDto getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCategoryDto serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public VendorDto getVendor() {
		return vendor;
	}

	public void setVendor(VendorDto vendor) {
		this.vendor = vendor;
	}


	

	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}
	public void addImage(ImageDto image) {
		images.add(image);
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
