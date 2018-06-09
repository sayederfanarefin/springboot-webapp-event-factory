package com.avnrsol.eventfactory.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "charge")
public class Booking {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	private Long id;

	@Column(name = "created_at")
	public Date createdAt;

	private Date startDate;

	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_service_booking")
	private Serviceo service;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Serviceo getService() {
		return service;
	}

	public void setService(Serviceo service) {
		this.service = service;
	}
}
