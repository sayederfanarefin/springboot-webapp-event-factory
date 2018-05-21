package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.Image;

public interface IImageService {
	Image findById(Long id);
    void delete(Image image);
    Image updateImage(Image image);
    
    Page<Image> findAllImage( int page);
    
}
