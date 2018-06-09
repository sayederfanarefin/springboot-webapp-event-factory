package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.Serviceo;
import com.avnrsol.eventfactory.Repository.ServiceoRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.interfaces.IServiceoService;

import java.util.List;

@Service
@Transactional
public class ServiceoService implements IServiceoService {

	@Autowired
	private ServiceoRepository serviceoRepository;

	@Override
	public Serviceo findById(Long id) {
		return serviceoRepository.findServiceoById(id);
	}

	@Override
	public void delete(Serviceo serviceo) {
		serviceoRepository.delete(serviceo);
	}

	@Override
	public Serviceo updateServiceo(Serviceo serviceo) {
		return serviceoRepository.save(serviceo);
	}

	@Override
	public Page<Serviceo> findAllServiceo(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return serviceoRepository.findAll(request);
	}

	@Override
	public Serviceo add(Serviceo serviceo) {
		
		return serviceoRepository.save(serviceo);
	}

	@Override
	public List<Serviceo> search(String search) {

System.out.println("seraching for "+ search);

		return serviceoRepository.findByNameContaining(search);
	}


}