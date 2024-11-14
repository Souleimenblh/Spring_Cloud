package com.souleimen.avions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
import org.springframework.web.multipart.MultipartFile;

import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.entities.Image;
import com.souleimen.avions.service.AvionService;
import com.souleimen.avions.service.ImageService; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List; 
 
@RestController 
@RequestMapping("/api/image")
@CrossOrigin(origins = "*") 
public class ImageRestController { 
	 
    @Autowired 
    ImageService  imageService ; 
    
    @Autowired
    AvionService avionService;
   
    @RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST) 
    public void uploadImageFS(@RequestParam("image") MultipartFile 
    		file,@PathVariable("id") Long id) throws IOException { 
    	Avion p = avionService.getAvion(id); 
    	p.setImagePath(id+".jpg"); 

    	Files.write(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())
    			, file.getBytes()); 
    	avionService.saveAvion(p); 

    } 

    @RequestMapping(value = "/loadfromFS/{id}" ,  
    		method = RequestMethod.GET, 
    		produces = MediaType.IMAGE_JPEG_VALUE) 
    public byte[] getImageFS(@PathVariable("id") Long id) throws IOException { 

    	Avion p = avionService.getAvion(id); 
    	return 
    			Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())); 
    } 
    
   
    @RequestMapping(value = "/upload" , method = RequestMethod.POST) 
    public Image uploadImage(@RequestParam("image")MultipartFile file) throws IOException { 
        return imageService.uplaodImage(file); 
    } 
    @RequestMapping(value = "/get/info/{id}" , method = RequestMethod.GET) 
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException { 
        return imageService.getImageDetails(id) ; 
    } 
    @RequestMapping(value = "/load/{id}" , method = RequestMethod.GET) 
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException 
{ 
        return imageService.getImage(id); 
    } 
     
   
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE) 
    public void deleteImage(@PathVariable("id") Long id){ 
        imageService.deleteImage(id); 
    } 
    @RequestMapping(value="/update",method = RequestMethod.PUT) 
    public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException { 
        return imageService.uplaodImage(file); 
    } 
    
//    @PostMapping(value = "/uplaodImageAvio/{idAvio}" ) 
//    public Image uploadMultiImages(@RequestParam("image")MultipartFile file, 
//                @PathVariable("idAvio") Long idAvio)  
//		throws IOException { 
//		       return imageService.uplaodImageAvio(file,idAvio); 
//	}
    
    
    @PostMapping(value = "/uplaodImageAvio/{idAvio}" )
	 public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
			 @PathVariable("idAvio") Long idAvio) 
					 throws IOException {
		 return imageService.uplaodImageAvio(file,idAvio);
	 }
    
		
		@RequestMapping(value = "/getImagesAvio/{idAvio}",method = RequestMethod.GET) 
		   public List<Image> getImagesAvio(@PathVariable("idAvio") Long idAvio) 
		throws IOException { 
		      return imageService.getImagesParAvio(idAvio); 
		   } 
    
    
    
}