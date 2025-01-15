package com.cybersoft.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.repository.FoodRepository;
import com.cybersoft.osahaneat.service.imp.MenuServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	MenuServiceImp menuServiceImp;
	
	@PostMapping("/createMenu")
	public ResponseEntity<?> createMenu(@RequestParam MultipartFile file,
											  @RequestParam String title,
											  @RequestParam boolean is_freeship,
											  @RequestParam String time_ship,
											  @RequestParam double price,
											  @RequestParam int cate_id){
		
		ResponseData responseData = new ResponseData();
		boolean isSuccess = menuServiceImp.createMenu(file, title, is_freeship, time_ship, price, cate_id);
		responseData.setData(isSuccess);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
}
