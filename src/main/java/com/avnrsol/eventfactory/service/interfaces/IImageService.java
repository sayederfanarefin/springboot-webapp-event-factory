package com.avnrsol.eventfactory.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.avnrsol.eventfactory.Model.Image;


public interface IImageService {
	
	Image addImage(Image image);
	void deleteImage(Image image);
	Image findById(Long id);
	Image findByUrl(String url);
	
	Image updateImage(Image image);
	
	
	List<Image> saveUploadedFiles(List<MultipartFile> files) throws IOException;
}
