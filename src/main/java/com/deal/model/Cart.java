package com.deal.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cart {
	
	@Id
	private String id;
	
	private String userName;
	
	private List<CartProduct> products;
	
	public Cart() {}

	public Cart(String userName, List<CartProduct> products) {
		this.userName = userName;
		this.products = products;
	}
	
	
}
