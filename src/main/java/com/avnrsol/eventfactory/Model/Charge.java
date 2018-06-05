package com.avnrsol.eventfactory.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "charge")
public class Charge {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_id")
	private Long id;


	public double deliveryCharge;
	
	public double serviceChargeTire1;
	public double serviceChargeTire2;
	public double serviceChargeTire3;

	public double serviceChargeTire1LowerLimit;
	public double serviceChargeTire2LowerLimit;
	public double serviceChargeTire3LowerLimit;

	public double serviceChargeTire1UpperLimit;
	public double serviceChargeTire2UpperLimit;
	public double serviceChargeTire3UpperLimit;

	public double vendorDiscount;


	
	public Charge() {
		super();
	}

	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	public double getDeliveryCharge() {
		return deliveryCharge;
	}


	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public double getServiceChargeTire1() {
		return serviceChargeTire1;
	}

	public void setServiceChargeTire1(double serviceChargeTire1) {
		this.serviceChargeTire1 = serviceChargeTire1;
	}

	public double getServiceChargeTire2() {
		return serviceChargeTire2;
	}

	public void setServiceChargeTire2(double serviceChargeTire2) {
		this.serviceChargeTire2 = serviceChargeTire2;
	}

	public double getServiceChargeTire3() {
		return serviceChargeTire3;
	}

	public void setServiceChargeTire3(double serviceChargeTire3) {
		this.serviceChargeTire3 = serviceChargeTire3;
	}

	public double getServiceChargeTire1LowerLimit() {
		return serviceChargeTire1LowerLimit;
	}

	public void setServiceChargeTire1LowerLimit(double serviceChargeTire1LowerLimit) {
		this.serviceChargeTire1LowerLimit = serviceChargeTire1LowerLimit;
	}

	public double getServiceChargeTire2LowerLimit() {
		return serviceChargeTire2LowerLimit;
	}

	public void setServiceChargeTire2LowerLimit(double serviceChargeTire2LowerLimit) {
		this.serviceChargeTire2LowerLimit = serviceChargeTire2LowerLimit;
	}

	public double getServiceChargeTire3LowerLimit() {
		return serviceChargeTire3LowerLimit;
	}

	public void setServiceChargeTire3LowerLimit(double serviceChargeTire3LowerLimit) {
		this.serviceChargeTire3LowerLimit = serviceChargeTire3LowerLimit;
	}

	public double getServiceChargeTire1UpperLimit() {
		return serviceChargeTire1UpperLimit;
	}

	public void setServiceChargeTire1UpperLimit(double serviceChargeTire1UpperLimit) {
		this.serviceChargeTire1UpperLimit = serviceChargeTire1UpperLimit;
	}

	public double getServiceChargeTire2UpperLimit() {
		return serviceChargeTire2UpperLimit;
	}

	public void setServiceChargeTire2UpperLimit(double serviceChargeTire2UpperLimit) {
		this.serviceChargeTire2UpperLimit = serviceChargeTire2UpperLimit;
	}

	public double getServiceChargeTire3UpperLimit() {
		return serviceChargeTire3UpperLimit;
	}

	public void setServiceChargeTire3UpperLimit(double serviceChargeTire3UpperLimit) {
		this.serviceChargeTire3UpperLimit = serviceChargeTire3UpperLimit;
	}

	public double getVendorDiscount() {
		return vendorDiscount;
	}

	public void setVendorDiscount(double vendorDiscount) {
		this.vendorDiscount = vendorDiscount;
	}
}
