package com.deal.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.deal.model.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	//private Set<String> roles;

	@NotBlank
	@Size(min = 6, max = 100)
	private String password;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	private Long phone;
	
	private Address address;

}
