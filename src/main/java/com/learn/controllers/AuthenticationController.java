package com.learn.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dtos.AuthenticationReaponse;
import com.learn.dtos.AuthenticationRequest;
import com.learn.service.jwt.UserDetailsServiceImpl;
import com.learn.utils.JwtUtils;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/authenticaiton")
	public AuthenticationReaponse createAuthenticationToken
			(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) 
					throws BadCredentialsException,DisabledException,UsernameNotFoundException,IOException{
		
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect UserName and Password");
		}catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not Present, please Register User");
			return null;
		}
		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtils.generateToken(userDetails.getUsername());
		return new AuthenticationReaponse(jwt);
	}
}
