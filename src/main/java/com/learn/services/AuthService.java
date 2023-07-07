package com.learn.services;

import com.learn.dtos.SignupRequest;
import com.learn.dtos.UserDTO;

public interface AuthService {

	UserDTO createUser(SignupRequest signupRequest);


}
