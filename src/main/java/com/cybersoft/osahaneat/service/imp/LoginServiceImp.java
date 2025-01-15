package com.cybersoft.osahaneat.service.imp;

import java.util.List;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.payload.SignUpRequest;

public interface LoginServiceImp {
	List<UserDTO> getAllUser();
	boolean checkLogin(String username, String password);
	boolean addUser(SignUpRequest signUpRequest);
}
