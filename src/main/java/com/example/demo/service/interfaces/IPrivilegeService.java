package com.example.demo.service.interfaces;

import org.springframework.data.domain.Page;

import com.example.demo.Model.Privilege;

public interface IPrivilegeService {
	Privilege findByName(String name);
	Privilege findById(Long id);
    void delete(Privilege privilege);
    Privilege updatePrivilege(Privilege privilege);
    
    Page<Privilege> findAllPrivilege( int page);
    
    
 
}
