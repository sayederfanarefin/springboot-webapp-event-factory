package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Role;

public interface IRoleService {
	Role findByName(String name);
	Role findById(long id);
    void delete(Role role);
    Role updateRole(Role role);
    
    Page<Role> findAllRole( int page);
    Role findByUsers_Id(Long userId);
    
    
   
}
