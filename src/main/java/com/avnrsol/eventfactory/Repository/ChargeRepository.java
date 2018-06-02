package com.avnrsol.eventfactory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.Charge;

@Repository("chargeRepository")
public interface ChargeRepository extends JpaRepository<Charge, Long>{
	Charge findById(Long id);
}