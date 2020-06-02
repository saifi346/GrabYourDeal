package com.deal.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Reviews {

	private String Id;

	private String productName;

	private List<SingleReview> review;
	
	public Reviews() {
		
	}

	public Reviews(String productName, List<SingleReview> review) {
		this.productName = productName;
		this.review = review;
	}

}
