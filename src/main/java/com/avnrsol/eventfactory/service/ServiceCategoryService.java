package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.ServiceCategory;
import com.avnrsol.eventfactory.Repository.ServiceCategoryRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.interfaces.IServiceCategoryService;

@Service
@Transactional
public class ServiceCategoryService implements IServiceCategoryService {

	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	@Override
	public ServiceCategory findById(Long id) {
		return serviceCategoryRepository.findServiceCategoryById(id);
	}

	@Override
	public void delete(ServiceCategory serviceCategory) {
		serviceCategoryRepository.delete(serviceCategory);
	}

	@Override
	public ServiceCategory updateServiceCategory(ServiceCategory serviceCategory) {
		return serviceCategoryRepository.save(serviceCategory);
	}

	@Override
	public Page<ServiceCategory> findAllServiceCategory(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return serviceCategoryRepository.findAll(request);
	}

	@Override
	public ServiceCategory add(ServiceCategory serviceCategory) {
		
		return serviceCategoryRepository.save(serviceCategory);
	}

	
}