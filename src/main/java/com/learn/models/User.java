package com.learn.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "learnUser")
@Data
public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	private String name;
	private String email;
	private String password;
	private String phone;
}
