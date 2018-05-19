package com.avnrsol.eventfactory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.ServiceCategory;

@Repository("serviceCategoryRepository")
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer>{
	ServiceCategory findServiceCategoryById(Long id);
	ServiceCategory findByName(String name);
}