package com.deal.model;



import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
	@Id
	private String id;

	private String title;

	private String productName;

	private String productDescription;

	private String category;

	private int price;

	private int stock;

	private String weight;

	private String dimensions;

	private String materials;

	private List<String> color;

	private List<String> size;

	String encodedImage;
	
	public Product() {
		
	}
	
	public Product(String id, String title, String productName, String productDescription, String category, int price,
			int stock, String weight, String dimensions, String materials, List<String> color, List<String> size,
			String encodedImage) {
		this.id = id;
		this.title = title;
		this.productName = productName;
		this.productDescription = productDescription;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.weight = weight;
		this.dimensions = dimensions;
		this.materials = materials;
		this.color = color;
		this.size = size;
		this.encodedImage = encodedImage;
	}

}

