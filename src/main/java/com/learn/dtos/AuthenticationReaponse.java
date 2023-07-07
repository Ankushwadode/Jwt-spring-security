package com.learn.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationReaponse {
	
	private String jwt;
}
