package com.deal.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
	
	@Id
	private String id;
	
	private String username;
	
	private List<OrderProduct> products;
	
	public Order() {
		
	}

	public Order(String username, List<OrderProduct> products) {
		this.username = username;
		this.products = products;
	}
	
}