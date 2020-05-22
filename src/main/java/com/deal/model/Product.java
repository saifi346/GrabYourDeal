package com.deal.model;



import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
	
	@Id
	private String id;
	
	String title;
	
	private String productName;
	
	private String productDescription;
	
	private String category;

	private int price;
	
	String encodedImage;
	
	public Product() {
		
	}

	public Product(String title, String productName,  String category, String productDescription, int price, String encodedImage) {
		super();
		this.title=title;
		this.productName = productName;
		this.productDescription = productDescription;
		this.category = category;
		this.price = price;
		this.encodedImage=encodedImage;
	}

}

