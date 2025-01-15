package com.cybersoft.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImp userServiceImp;
	
	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllUser(){
		ResponseData responseData = new ResponseData();
		responseData.setData(userServiceImp.getAllUser());
		return new ResponseEntity<>(responseData,HttpStatus.OK);
	}
}
