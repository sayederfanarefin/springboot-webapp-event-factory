package com.avnrsol.eventfactory.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.avnrsol.eventfactory.Model.Image;
import com.avnrsol.eventfactory.Repository.ImageRepository;
import com.avnrsol.eventfactory.service.interfaces.IImageService;
import com.avnrsol.eventfactory.storage.StorageService;


@Service
@Transactional
public class ImageService implements IImageService {

	@Autowired
	ImageRepository imageRepository;
	
	
	@Value("${file.download.base}")
	private String DOWNLOAD_FOLDER;

	private final StorageService storageService;
	
	 @Autowired
	    public ImageService(StorageService storageService) {
	        this.storageService = storageService;
	    }
	 
	    
	    

		@Value("${file.upload.url}")
		private String UPLOADED_FOLDER;
		
		
	
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
	
	
	@Override
	public List<Image> saveUploadedFiles(List<MultipartFile> files) throws IOException {

		List<Image> imagesToBeReturned = new ArrayList<Image>();
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(UPLOADED_FOLDER + getSaltString()+URLEncoder.encode(file.getOriginalFilename(), "UTF-8"));
//			
//			System.out.println(path.toString());
//			
//			
//			Files.write(path, bytes);
			
			String fileName = storageService.store(file);
			
			
			 Resource fileo = storageService.loadAsResource(fileName);
			 
			
			 System.out.println(fileo.getURL().toString());
			 
			
			 
			 String url = DOWNLOAD_FOLDER+"/"+fileo.getFilename().toString();
			 System.out.println("demo download url when u add the base url: " + url);
			 
			 
			 System.out.println(fileo.getFilename().toString());
			 
			 
			addImage( new Image("a", "a", fileo.getFilename().toString()));
			imagesToBeReturned.add(findByUrl(fileo.getFilename().toString()));

		}
		return imagesToBeReturned;
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 100) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return URLEncoder.encode(saltStr);

    }

}
