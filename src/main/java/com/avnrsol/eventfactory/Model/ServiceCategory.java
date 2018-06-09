package com.avnrsol.eventfactory.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "fk_service_category_image")
	private List<Image> images = new ArrayList<Image>();

	@Column(name = "created_at")
	public Date createdAt;


	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name = "fk_service_service_category")
	private List<Serviceo> Services = new ArrayList<Serviceo>();

	public ServiceCategory() {
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
