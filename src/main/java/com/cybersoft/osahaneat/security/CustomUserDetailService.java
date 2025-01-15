package com.cybersoft.osahaneat.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.print("" +username);
		Users users = userRepository.findByUserName(username);
		if(users == null) {
			throw new UsernameNotFoundException("User can not exist");
		}
		System.out.print("" +users.getUserName());
		return new User(username, users.getPassword(), new ArrayList<>());
	}
	
}
