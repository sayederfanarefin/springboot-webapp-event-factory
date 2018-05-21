package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Serviceo;

public interface IServiceoService {
	Serviceo findById(Long id);
    void delete(Serviceo service);
    Serviceo updateServiceo(Serviceo service);
    
    Page<Serviceo> findAllServiceo( int page);
	Serviceo add(Serviceo serviceo);
    
}
