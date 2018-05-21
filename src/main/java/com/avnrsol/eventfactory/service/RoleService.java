package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.Role;
import com.avnrsol.eventfactory.Repository.RoleRepository;
import com.avnrsol.eventfactory.configuration.Constants;
import com.avnrsol.eventfactory.service.interfaces.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Page<Role> findAllRole(int page) {
		PageRequest request = new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return roleRepository.findAll(request);
	}

	@Override
	public Role findByUsers_Id(Long userId) {
		return roleRepository.findByUsers_Id(userId);
	}

	
}