package com.avnrsol.eventfactory.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.Vendor;

@Repository("vendorRepository")
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	Vendor findById(Long id);
    Vendor findVendorByServices_Id(Long ServiceId);
    List<Vendor> findVendorByCity(String city);
    List<Vendor> findVendorByCountry(String country);
    Vendor findByName(String name);
}