package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.Vendor;
import com.avnrsol.eventfactory.Repository.VendorRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.interfaces.IVendorService;

@Service
@Transactional
public class VendorService implements IVendorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public Vendor findById(Long id) {
		return vendorRepository.findById(id);
	}

	@Override
	public void delete(Vendor vendor) {
		vendorRepository.delete(vendor);
	}

	@Override
	public Vendor updateVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	@Override
	public Page<Vendor> findAllVendor(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return vendorRepository.findAll(request);
	}

	@Override
	public Vendor add(Vendor vendor) {
		
		return vendorRepository.save(vendor);
	}

	
}