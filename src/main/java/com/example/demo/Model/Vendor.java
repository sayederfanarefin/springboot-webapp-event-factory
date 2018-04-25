package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vendor")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_id")
	private int id;
	
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	
	@Column(columnDefinition="LONGTEXT")
	private String address;
	
	
	private String phone;
	
	@Column(columnDefinition="LONGTEXT")
	private String description;
	
	@Column(name = "city")
	@NotEmpty(message = "*Please provide your city")
	private String city;
	
	@Column(name = "country")
	@NotEmpty(message = "*Please provide your country")
	private String country;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_service_vendor")
	private List<Service> services  = new ArrayList<Service>();
	

	public Vendor() {
		super();
	}




	public Vendor(String email, String name, String address, String phone, String description, String city,
			String country, List<Service> services) {
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




	public int getId() {
		return id;
	}




	public void setId(int id) {
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




	public List<Service> getServices() {
		return services;
	}




	public void setServices(List<Service> services) {
		this.services = services;
	}

	
}
