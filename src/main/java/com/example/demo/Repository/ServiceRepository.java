package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Service;

@Repository("serviceRepository")
public interface ServiceRepository extends JpaRepository<Service, Integer>{
	Service findServiceById(Long id);
    List<Service> findServiceByAvailable(boolean available);
    List<Service> findServiceByServiceCategory_Id(Long serviceCategoryId);
    List<Service> findServiceByVendor_Id(Long vendorId);
}