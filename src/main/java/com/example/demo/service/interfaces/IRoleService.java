package com.example.demo.service.interfaces;

import org.springframework.data.domain.Page;

import com.example.demo.Model.Role;

public interface IRoleService {
	Role findByName(String name);
	Role findById(long id);
    void delete(Role role);
    Role updateRole(Role role);
    
    Page<Role> findAllRole( int page);
    Role findByUsers_Id(Long userId);
    
    
   
}
