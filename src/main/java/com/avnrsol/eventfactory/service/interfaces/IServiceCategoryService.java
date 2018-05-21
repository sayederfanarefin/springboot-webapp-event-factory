package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.ServiceCategory;

public interface IServiceCategoryService {
	ServiceCategory findById(Long id);
    void delete(ServiceCategory serviceCategory);
    ServiceCategory updateServiceCategory(ServiceCategory serviceCategory);
    
    Page<ServiceCategory> findAllServiceCategory( int page);
	ServiceCategory add(ServiceCategory serviceCategory);
    
}
