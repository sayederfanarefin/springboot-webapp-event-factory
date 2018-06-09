package com.avnrsol.eventfactory.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vendor")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;

	@Column(name = "created_at")
	public Date createdAt;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	public double commision;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	public String name;
	
	
	@Column(columnDefinition="LONGTEXT")
	public String address;


	public String phone;

	public String zip;
	
	@Column(columnDefinition="LONGTEXT")
	public String description;
	
	@Column(name = "city")
	@NotEmpty(message = "*Please provide your city")
	public String city;
	
	@Column(name = "country")
	@NotEmpty(message = "*Please provide your country")
	public String country;
	
	
	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "fk_service_vendor")
	public List<Serviceo> services  = new ArrayList<Serviceo>();
	
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "fk_vendor_image")
	public List<Image> images = new ArrayList<Image>();
	

	public Vendor() {
		super();
	}


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


	public Vendor(String email, String name, String address, String phone, String description, String city,
			String country, List<Serviceo> services) {
		super();
		this.email = email;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.description = description;
		this.city = city;
		this.country = country;
		this.services = services;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getCountry() {
		return country;
	}




	public void setCountry(String country) {
		this.country = country;
	}




	public List<Serviceo> getServices() {
		return services;
	}




	public void setServices(List<Serviceo> services) {
		this.services = services;
	}

	public void addService(Serviceo service) {
		services.add(service);
	}




	public String getZip() {
		return zip;
	}




	public void setZip(String zip) {
		this.zip = zip;
	}




	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public double getCommision() {
		return commision;
	}




	public void setCommision(double commision) {
		this.commision = commision;
	}
	
	
	
	
}
