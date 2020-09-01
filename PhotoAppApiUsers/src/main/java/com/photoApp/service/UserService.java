package com.photoApp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.photoApp.dto.UserDto;


public interface UserService  extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	UserDto getUserDetailsByEmail(String email);
}
