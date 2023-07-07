package com.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RestController;

import com.learn.dtos.SignupRequest;
import com.learn.dtos.UserDTO;
import com.learn.services.AuthService;

@RestController
public class SignupUserController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
		UserDTO createdUser = authService.createUser(signupRequest);
		if (createdUser == null) {
			return new ResponseEntity<>("user is not created, try again later",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
}
