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


	public double deliveryChargeTire1;
	public double deliveryChargeTire2;
	public double deliveryChargeTire3;

	public double deliveryChargeTire1LowerLimit;
	public double deliveryChargeTire2LowerLimit;
	public double deliveryChargeTire3LowerLimit;

	public double deliveryChargeTire1UpperLimit;
	public double deliveryChargeTire2UpperLimit;
	public double deliveryChargeTire3UpperLimit;
	
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

	public int extra_charge_tier_1_days;
	public int extra_charge_tier_2_days;
	public int extra_charge_tier_3_days;

	public int extra_charge_tier_1_charge_percentage;
	public int extra_charge_tier_2_charge_percentage;
	public int extra_charge_tier_3_charge_percentage;

	public Charge() {
		super();
	}


	public int getExtra_charge_tier_1_days() {
		return extra_charge_tier_1_days;
	}

	public void setExtra_charge_tier_1_days(int extra_charge_tier_1_days) {
		this.extra_charge_tier_1_days = extra_charge_tier_1_days;
	}

	public int getExtra_charge_tier_2_days() {
		return extra_charge_tier_2_days;
	}

	public void setExtra_charge_tier_2_days(int extra_charge_tier_2_days) {
		this.extra_charge_tier_2_days = extra_charge_tier_2_days;
	}

	public int getExtra_charge_tier_3_days() {
		return extra_charge_tier_3_days;
	}

	public void setExtra_charge_tier_3_days(int extra_charge_tier_3_days) {
		this.extra_charge_tier_3_days = extra_charge_tier_3_days;
	}

	public int getExtra_charge_tier_1_charge_percentage() {
		return extra_charge_tier_1_charge_percentage;
	}

	public void setExtra_charge_tier_1_charge_percentage(int extra_charge_tier_1_charge_percentage) {
		this.extra_charge_tier_1_charge_percentage = extra_charge_tier_1_charge_percentage;
	}

	public int getExtra_charge_tier_2_charge_percentage() {
		return extra_charge_tier_2_charge_percentage;
	}

	public void setExtra_charge_tier_2_charge_percentage(int extra_charge_tier_2_charge_percentage) {
		this.extra_charge_tier_2_charge_percentage = extra_charge_tier_2_charge_percentage;
	}

	public int getExtra_charge_tier_3_charge_percentage() {
		return extra_charge_tier_3_charge_percentage;
	}

	public void setExtra_charge_tier_3_charge_percentage(int extra_charge_tier_3_charge_percentage) {
		this.extra_charge_tier_3_charge_percentage = extra_charge_tier_3_charge_percentage;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public double getDeliveryChargeTire1() {
		return deliveryChargeTire1;
	}

	public void setDeliveryChargeTire1(double deliveryChargeTire1) {
		this.deliveryChargeTire1 = deliveryChargeTire1;
	}

	public double getDeliveryChargeTire2() {
		return deliveryChargeTire2;
	}

	public void setDeliveryChargeTire2(double deliveryChargeTire2) {
		this.deliveryChargeTire2 = deliveryChargeTire2;
	}

	public double getDeliveryChargeTire3() {
		return deliveryChargeTire3;
	}

	public void setDeliveryChargeTire3(double deliveryChargeTire3) {
		this.deliveryChargeTire3 = deliveryChargeTire3;
	}

	public double getDeliveryChargeTire1LowerLimit() {
		return deliveryChargeTire1LowerLimit;
	}

	public void setDeliveryChargeTire1LowerLimit(double deliveryChargeTire1LowerLimit) {
		this.deliveryChargeTire1LowerLimit = deliveryChargeTire1LowerLimit;
	}

	public double getDeliveryChargeTire2LowerLimit() {
		return deliveryChargeTire2LowerLimit;
	}

	public void setDeliveryChargeTire2LowerLimit(double deliveryChargeTire2LowerLimit) {
		this.deliveryChargeTire2LowerLimit = deliveryChargeTire2LowerLimit;
	}

	public double getDeliveryChargeTire3LowerLimit() {
		return deliveryChargeTire3LowerLimit;
	}

	public void setDeliveryChargeTire3LowerLimit(double deliveryChargeTire3LowerLimit) {
		this.deliveryChargeTire3LowerLimit = deliveryChargeTire3LowerLimit;
	}

	public double getDeliveryChargeTire1UpperLimit() {
		return deliveryChargeTire1UpperLimit;
	}

	public void setDeliveryChargeTire1UpperLimit(double deliveryChargeTire1UpperLimit) {
		this.deliveryChargeTire1UpperLimit = deliveryChargeTire1UpperLimit;
	}

	public double getDeliveryChargeTire2UpperLimit() {
		return deliveryChargeTire2UpperLimit;
	}

	public void setDeliveryChargeTire2UpperLimit(double deliveryChargeTire2UpperLimit) {
		this.deliveryChargeTire2UpperLimit = deliveryChargeTire2UpperLimit;
	}

	public double getDeliveryChargeTire3UpperLimit() {
		return deliveryChargeTire3UpperLimit;
	}

	public void setDeliveryChargeTire3UpperLimit(double deliveryChargeTire3UpperLimit) {
		this.deliveryChargeTire3UpperLimit = deliveryChargeTire3UpperLimit;
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
