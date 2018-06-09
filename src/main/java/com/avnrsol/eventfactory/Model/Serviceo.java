package com.avnrsol.eventfactory.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "service")
public class Serviceo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	private Long id;

	@Column(name = "name")
	
	private String name;
	
	private double price;
	
	private double discount;

	@Column(name = "created_at")
	public Date createdAt;


	@OneToMany
    @JoinColumn(name = "fk_order_item_service")
	public List<OrderItem> orderItems;
	

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "available")
	
	private boolean available;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_service_image")
	private List<Image> images = new ArrayList<Image>();

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_service_service_category")
	@JsonBackReference
	private ServiceCategory serviceCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_service_vendor")
	@JsonBackReference
	private Vendor vendor;


	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "fk_service_booking")
	public List<Booking> booking  = new ArrayList<Booking>();

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.000 ", timezone="UTC")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Serviceo() {
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

	

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}


	public Serviceo(String name, String description, boolean available, List<Image> images,
			ServiceCategory serviceCategory, Vendor vendor) {
		super();
		this.name = name;
		this.description = description;
		this.available = available;
		this.images = images;
		this.serviceCategory = serviceCategory;
		this.vendor = vendor;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}
	public void addImage(Image image) {
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


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}
}
