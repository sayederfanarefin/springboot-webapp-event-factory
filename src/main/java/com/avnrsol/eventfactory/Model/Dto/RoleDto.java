package com.avnrsol.eventfactory.Model.Dto;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public class RoleDto implements Serializable {


    private Long id;

   
    
    private Collection<PrivilegeDto> privileges;

    private String name;

    public RoleDto() {
        super();
    }

    public RoleDto(final String name) {
        super();
        this.name = name;
    }

    //

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

   

    public Collection<PrivilegeDto> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Collection<PrivilegeDto> privileges) {
        this.privileges = privileges;
    }


}