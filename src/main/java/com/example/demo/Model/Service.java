package com.example.demo.Model;

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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	private int id;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "available")
	@NotEmpty(message = "*Please provide your availablility of product")
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

	public Service() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	public Service(String name, String description, boolean available, List<Image> images,
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

	
}
