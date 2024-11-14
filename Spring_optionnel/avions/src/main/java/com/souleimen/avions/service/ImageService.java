package com.souleimen.avions.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.multipart.MultipartFile; 
import com.souleimen.avions.entities.Image; 
 
public interface ImageService { 
     Image uplaodImage(MultipartFile file) throws IOException; 
     Image getImageDetails(Long id) throws IOException; 
     ResponseEntity<byte[]> getImage(Long id) throws IOException; 
     void deleteImage(Long id); 
     
     Image uplaodImageAvio(MultipartFile file,Long idAvio) throws IOException; 
     List<Image> getImagesParAvio(Long avioId);
     
} 