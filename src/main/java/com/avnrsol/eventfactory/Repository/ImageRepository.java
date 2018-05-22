package com.avnrsol.eventfactory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avnrsol.eventfactory.Model.Image;

@Repository("imageRepository")
public interface ImageRepository extends JpaRepository<Image, Long>{
	Image findImageById(Long id);
	Image findImageByUrl(String url);
	Image findById(Long id);
}