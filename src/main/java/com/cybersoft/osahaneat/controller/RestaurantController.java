package com.cybersoft.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;


//@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	FileServiceImp fileServiceImp;
	
	@Autowired
	RestaurantServiceImp restaurantServiceImp;
	
	
	@PostMapping("/createRestaurant")
	public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
											  @RequestParam String title,
											  @RequestParam String subtitle,
											  @RequestParam String description,
											  @RequestParam boolean is_freeship,
											  @RequestParam String address,
											  @RequestParam String open_date){
		
		ResponseData responseData = new ResponseData();
		boolean isSuccess = restaurantServiceImp.insertRestaurant(file, title, subtitle, description, is_freeship, address, open_date);
		responseData.setData(isSuccess);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@GetMapping("/getMenuRestaurant")
	public ResponseEntity<?> getMenuRestaurant(){
	
		ResponseData responseData = new ResponseData();
		
		responseData.setData(restaurantServiceImp.getMenuRestaurant());
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@GetMapping("/files/{filename:.+}")
	
	public ResponseEntity<?> getFileRestaurant(@PathVariable String filename){
		
		Resource resource = fileServiceImp.loadFile(filename);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<?> getDetailRestaurant(@RequestParam int id){
			
			ResponseData responseData = new ResponseData();
			responseData.setData(restaurantServiceImp.getDetailRestaurant(id));
			
			return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
}
