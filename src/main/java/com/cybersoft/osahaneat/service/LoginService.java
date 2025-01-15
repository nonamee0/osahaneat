package com.cybersoft.osahaneat.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Roles;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.SignUpRequest;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;

@Service
public class LoginService implements LoginServiceImp{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserDTO> getAllUser(){
		List<Users> listUser = userRepository.findAll();
		List<UserDTO> listUserDTO = new ArrayList<>();
		
		for(Users users : listUser) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(users.getId());
			userDTO.setUserName(users.getUserName());
			userDTO.setFullName(users.getFullName());
			userDTO.setPassword(users.getPassword());
			listUserDTO.add(userDTO);
		}
		
		return listUserDTO;
	}

	@Override
	public boolean addUser(SignUpRequest signUpRequest) {
		// TODO Auto-generated method stub
		
		Roles roles = new Roles();
		roles.setId(signUpRequest.getRoleId());
		
		Users users = new Users();
		users.setFullName(signUpRequest.getFullname());
		users.setUserName(signUpRequest.getEmail());
		users.setPassword(signUpRequest.getPassword());
		users.setRoles(roles);
		try {
			userRepository.save(users);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	@Override
	public boolean checkLogin(String username, String password) {
		Users user = userRepository.findByUserName(username);
		return passwordEncoder.matches(password, user.getPassword());
	}
	
	
	
}
