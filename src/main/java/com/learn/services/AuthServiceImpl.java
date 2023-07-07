package com.learn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.dtos.SignupRequest;
import com.learn.dtos.UserDTO;
import com.learn.models.User;
import com.learn.reprositories.UserRepo;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(SignupRequest signupRequest) {
		
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setName(signupRequest.getName());
		user.setPhone(signupRequest.getPhone());
		
		User createdUser = userRepo.save(user);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(createdUser.getId());
		userDTO.setEmail(createdUser.getEmail());
		userDTO.setPassword(createdUser.getPassword());
		userDTO.setName(createdUser.getName());
		userDTO.setPhone(createdUser.getPhone());
		
		return userDTO;
	}

}
