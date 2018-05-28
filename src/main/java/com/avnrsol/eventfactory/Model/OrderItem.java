//package com.avnrsol.eventfactory.Model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.validator.constraints.NotEmpty;
//import org.springframework.beans.factory.annotation.Value;
//
//@Entity
//
//public class OrderItem {
//	
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@Column(name = "name")
//	@NotEmpty(message = "*Please provide your name")
//	private String name;
//	
//	
//	@Column(columnDefinition="LONGTEXT")
//	private String description;
//	
//	@Column(columnDefinition="LONGTEXT")
//	private String url;
//	
//	
//	
//	public OrderItem() {
//		super();
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//
//
//	public OrderItem(String name, String description, String url) {
//		super();
//		this.name = name;
//		this.description = description;
//		this.url = url;
//	}
//
//
//
//	public Long getId() {
//		return id;
//	}
//
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	
//	
//}
