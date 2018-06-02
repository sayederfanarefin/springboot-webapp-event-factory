package com.avnrsol.eventfactory.Model.Dto;

import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ServiceCategoryDto {

	private Long id;
	
	
	private String name;
	
	
	private String description;
	
	
	private List<ImageDto> images = new ArrayList<ImageDto>();
	
	@JsonManagedReference(value="service-category-servie")
	private List<ServiceoDto> Services = new ArrayList<ServiceoDto>();

	public ServiceCategoryDto() {
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

	public List<ServiceoDto> getServices() {
		return Services;
	}

	public void setServices(List<ServiceoDto> services) {
		Services = services;
	}

	public ServiceCategoryDto(String name, String description, List<ImageDto> images, List<ServiceoDto> services) {
		super();
		this.name = name;
		this.description = description;
		this.images = images;
		Services = services;
	}

	public void addImage(ImageDto image) {
		images.add(image);
	}
}
