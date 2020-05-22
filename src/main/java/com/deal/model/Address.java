package com.deal.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

	private String addressLine;

	private String city;

	private String state;
	
	private Integer zipcode;

	public Address(String addressLine, String city, String state, Integer zipcode) {

		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

}
