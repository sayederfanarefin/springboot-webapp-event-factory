package com.avnrsol.eventfactory.Model.Dto;

import java.util.Collection;



import com.fasterxml.jackson.annotation.JsonBackReference;



public class PrivilegeDto {
   
    private Long id;

    private String name;

    private String tag;
    
    
    public PrivilegeDto() {
        super();
    }

    public PrivilegeDto(final String name) {
        super();
        this.name = name;
    }

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


	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
    
}
