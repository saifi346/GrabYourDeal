package com.deal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistProduct {

	private String productName;

	private int price;
	
    private String encodedUrl;
	
	public WishlistProduct() {

	}

	public WishlistProduct(String productName, int price, String encodedUrl) {
		this.productName = productName;
		this.price = price;
		this.encodedUrl = encodedUrl;
	}
}
