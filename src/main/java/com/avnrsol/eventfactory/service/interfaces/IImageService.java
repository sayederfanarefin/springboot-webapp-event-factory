package com.avnrsol.eventfactory.service.interfaces;

import com.avnrsol.eventfactory.Model.Image;


public interface IImageService {
	
	Image addImage(Image image);
	void deleteImage(Image image);
	Image findById(Long id);
	Image findByUrl(String url);
	
	Image updateImage(Image image);
	
}
