package com.cybersoft.osahaneat.controller;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.payload.SignUpRequest;
import com.cybersoft.osahaneat.save.JwtUtilHelper;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.service.LoginService;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginServiceImp loginServiceImp;
	
	@Autowired
	JwtUtilHelper jwtUtilHelper;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
		ResponseData responseData = new ResponseData();
		
//		SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String e = Encoders.BASE64.encode(secretKey.getEncoded());
//		System.out.print("api-api");
//		System.out.print(e);
//		System.out.print("api-api");
		
		if(loginServiceImp.checkLogin(username, password)) {
//			responseData.setData(true);
			String token = jwtUtilHelper.generateToken(username);
			responseData.setData(token);
		} else {
			responseData.setData("");
			responseData.setSuccess(false);
		}
		
		
		return new ResponseEntity<>(responseData,HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
		ResponseData responseData = new ResponseData();
		responseData.setData(loginServiceImp.addUser(signUpRequest));
		
		return new ResponseEntity<>(responseData,HttpStatus.OK);
	}
	
	@GetMapping("getAllUser")
	public ResponseEntity<?> getAllUser(){
		ResponseData responseData = new ResponseData();
		responseData.setData(loginServiceImp.getAllUser());
		return new ResponseEntity<>(responseData,HttpStatus.OK);
	}
	
	
	
}
