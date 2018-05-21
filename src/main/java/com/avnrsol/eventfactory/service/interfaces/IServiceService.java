package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Service;

public interface IServiceService {
	Service findById(Long id);
    void delete(Service service);
    Service updateService(Service service);
    
    Page<Service> findAllService( int page);
    
}
