package com.learn.dtos;

import lombok.Data;

@Data
public class UserDTO {
	
	private long id;
	private String name;
	private String email;
	private String password;
	private String phone;
}
