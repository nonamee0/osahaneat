package com.cybersoft.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;

@Service
public class UserService implements UserServiceImp{

	@Autowired
	UserRepository userRepository;
	
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

}
