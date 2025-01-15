package com.cybersoft.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;

@CrossOrigin()
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryServiceImp categoryServiceImp;
	
	@GetMapping("/getCategoryHomePage")
	public ResponseEntity<?> getCategoryHomePage(){
	
		ResponseData responseData = new ResponseData();
		
		responseData.setData(categoryServiceImp.getCategoryHomePage());
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
}
