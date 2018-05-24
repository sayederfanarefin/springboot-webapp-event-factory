package com.avnrsol.eventfactory.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.Serviceo;

@Repository("serviceRepository")
public interface ServiceoRepository extends JpaRepository<Serviceo, Long>{
	Serviceo findServiceoById(Long id);
    List<Serviceo> findServiceoByAvailable(boolean available);
    List<Serviceo> findServiceoByServiceCategory_Id(Long serviceCategoryId);
    List<Serviceo> findServiceoByVendor_Id(Long vendorId);
    List<Serviceo> findTop5ServiceoByServiceCategory_Id(Long serviceCategoryId);
}