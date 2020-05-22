package com.deal.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "user")
public class User {
	
	@Id
	private String id;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	private Long phone;
	
	private Address address;
	
	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 150)
	private String password;

	@DBRef
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}

	public User(String username, String email, String password, String name, Long phone) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name=name;
		this.phone=phone;
	}


}
