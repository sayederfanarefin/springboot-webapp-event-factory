package com.avnrsol.eventfactory.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity
@Table(name = "image")
public class Image {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	private Long id;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(name = "created_at")
	public Date createdAt;


	@Column(columnDefinition="LONGTEXT")
	private String description;
	
	@Column(columnDefinition="LONGTEXT")
	private String url;

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


	public Image() {
		super();
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

	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Image(String name, String description, String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	
	
}
