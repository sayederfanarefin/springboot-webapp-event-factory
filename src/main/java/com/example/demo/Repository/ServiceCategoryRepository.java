package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.ServiceCategory;

@Repository("serviceCategoryRepository")
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer>{
	ServiceCategory findServiceCategoryById(Long id);
}