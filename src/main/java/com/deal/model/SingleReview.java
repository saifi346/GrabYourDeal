package com.deal.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleReview {

	private String name;

	private String email;

	private int rating;

	private String comment;
	
	public SingleReview() {
		
	}

	public SingleReview(String name, String email, int rating, String comment) {
		this.name = name;
		this.email = email;
		this.rating = rating;
		this.comment = comment;
	}

}
