package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Privilege;

public interface IPrivilegeService {
	Privilege findByName(String name);
	Privilege findById(Long id);
    void delete(Privilege privilege);
    Privilege updatePrivilege(Privilege privilege);
    
    Page<Privilege> findAllPrivilege( int page);
    
    
 
}
