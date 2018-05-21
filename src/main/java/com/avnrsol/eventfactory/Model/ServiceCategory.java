package com.avnrsol.eventfactory.Model;

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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "service_category")
public class ServiceCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_category_id")
	private Long id;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	
	@Column(columnDefinition="LONGTEXT")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_service_category_image")
	private List<Image> images = new ArrayList<Image>();
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_service_service_category")
	private List<Serviceo> Services = new ArrayList<Serviceo>();

	public ServiceCategory() {
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

	public List<Serviceo> getServices() {
		return Services;
	}

	public void setServices(List<Serviceo> services) {
		Services = services;
	}

	public ServiceCategory(String name, String description, List<Image> images, List<Serviceo> services) {
		super();
		this.name = name;
		this.description = description;
		this.images = images;
		Services = services;
	}

	public void addImage(Image image) {
		images.add(image);
	}
}
