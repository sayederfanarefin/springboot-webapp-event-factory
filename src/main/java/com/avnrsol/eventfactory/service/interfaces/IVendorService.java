package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Vendor;

public interface IVendorService {
	Vendor findById(Long id);
    void delete(Vendor vendor);
    Vendor updateVendor(Vendor vendor);
    
    Vendor add(Vendor vendor);
    Page<Vendor> findAllVendor( int page);
    
}
