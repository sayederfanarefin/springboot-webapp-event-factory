package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Privilege;
import com.example.demo.Repository.PrivilegeRepository;
import com.example.demo.configuration.Constants;
import com.example.demo.service.interfaces.IPrivilegeService;

@Service
@Transactional
public class PrivilegeService implements IPrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;

	

	@Override
	public Privilege findByName(String name) {

		return privilegeRepository.findByName(name);
	}

	@Override
	public Privilege findById(Long id) {
		return privilegeRepository.findById(id);
	}

	@Override
	public void delete(Privilege privilege) {
		privilegeRepository.delete(privilege);
	}

	@Override
	public Privilege updatePrivilege(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	@Override
	public Page<Privilege> findAllPrivilege(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return privilegeRepository.findAll(request);
	}

	

}