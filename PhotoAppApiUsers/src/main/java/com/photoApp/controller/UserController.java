package com.photoApp.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photoApp.dto.UserDto;
import com.photoApp.model.CreateUserRequestModel;
import com.photoApp.model.CreateUserResponseModel;
import com.photoApp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	Environment env;

	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working" + env.getProperty("local.server.port");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML},
			     produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel user) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setEncryptedPassword("test"+UUID.randomUUID().toString());
		UserDto newUserDto= userService.createUser(userDto);
		CreateUserResponseModel createUserResponseModel = modelMapper.map(newUserDto,CreateUserResponseModel.class);
		return new ResponseEntity<CreateUserResponseModel>(createUserResponseModel,HttpStatus.CREATED);
	}

}
