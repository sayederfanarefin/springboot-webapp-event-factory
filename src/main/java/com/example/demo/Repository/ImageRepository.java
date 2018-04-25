package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Image;

@Repository("imageRepository")
public interface ImageRepository extends JpaRepository<Image, Integer>{
	Image findImageById(Long id);
	Image findImageByUrl(String url);
}