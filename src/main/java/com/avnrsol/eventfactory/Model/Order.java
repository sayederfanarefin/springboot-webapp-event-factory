package com.avnrsol.eventfactory.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	public Long id;
	
	@Column(columnDefinition="LONGTEXT")
	public String note;
	
	public Long subTotal;
	
	public String cuponCode;
	
	public Long total;
	
	public String paymentType;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
	public User user;
	
	@Column(name = "created_at")
    public Date createdAt;
	
//	@OneToMany
//    @JoinColumn(name = "fk_order_item")
//	public List<OrderItem> orderItems;


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public Long getSubTotal() {
		return subTotal;
	}



	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}



	public String getCuponCode() {
		return cuponCode;
	}



	public void setCuponCode(String cuponCode) {
		this.cuponCode = cuponCode;
	}



	public Long getTotal() {
		return total;
	}



	public void setTotal(Long total) {
		this.total = total;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
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



//	public List<OrderItem> getOrderItems() {
//		return orderItems;
//	}
//
//
//
//	public void setOrderItems(List<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}



	public String getPaymentType() {
		return paymentType;
	}



	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	
	
}
