package com.learn.service.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.learn.models.User; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learn.reprositories.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user =  userRepo.findFirstByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found",null);
		}
		
		return new org.springframework.security.core.userdetails.User
				(user.getEmail(),user.getPassword(),new ArrayList<>());
	}
	
}
