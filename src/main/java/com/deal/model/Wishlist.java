package com.deal.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Wishlist {

	private String id;

	private String username;

	private List<WishlistProduct> wishlistProducts;

	public Wishlist() {
	}

	public Wishlist(String username, List<WishlistProduct> wishlistProducts) {
		this.username = username;
		this.wishlistProducts = wishlistProducts;
	}
}
