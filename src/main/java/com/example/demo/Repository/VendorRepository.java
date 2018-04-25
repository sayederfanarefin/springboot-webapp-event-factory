package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Vendor;

@Repository("vendorRepository")
public interface VendorRepository extends JpaRepository<Vendor, Integer>{
	Vendor findVendorById(Long id);
    Vendor findVendorByServices_Id(Long ServiceId);
    List<Vendor> findVendorByCity(String city);
    List<Vendor> findVendorByCountry(String country);
}