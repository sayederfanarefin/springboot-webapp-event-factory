package com.avnrsol.eventfactory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avnrsol.eventfactory.Model.Image;
import com.avnrsol.eventfactory.Repository.ImageRepository;
import com.avnrsol.eventfactory.service.interfaces.IImageService;


@Service
@Transactional
public class ImageService implements IImageService {

	@Autowired
	ImageRepository imageRepository;
	
	
	@Override
	public Image addImage(Image image) {
		
		Image i =findByUrl(image.getUrl());
		if(i==null) {
			return imageRepository.save(image);
		}
		return i;
	}
	@Override
	public void deleteImage(Image image) {
		
		imageRepository.delete(image);
		
	}
	@Override
	public Image findById(Long id) {
		
		return imageRepository.findById(id);
	}
	@Override
	public Image findByUrl(String url) {
		
		return imageRepository.findImageByUrl(url);
	}
	@Override
	public Image updateImage(Image image) {
		// TODO Auto-generated method stub
		return imageRepository.save(image);
	}
	
	

}
