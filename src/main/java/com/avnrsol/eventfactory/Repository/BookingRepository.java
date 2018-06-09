package com.avnrsol.eventfactory.Repository;

import com.avnrsol.eventfactory.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Long>{

	Booking findById(Long id);
    List<Booking> findAllByService_Id(Long id);
    List<Booking> findAllByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);

}