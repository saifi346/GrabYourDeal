package com.deal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.deal.model.Reviews;

@CrossOrigin("*")
@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Value("${reviews.URL}")
	private String url;

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/getreviews/{productName}")
	public ResponseEntity<Reviews> getAllReviews(@PathVariable("productName") String productName, @RequestParam("count") int count)  {
		Reviews reviews = restTemplate.getForObject(url + "/getreviews/" +productName+ "?count="+count, Reviews.class);
		return new ResponseEntity<>(reviews, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addreview")
	public ResponseEntity<?> addReview(@RequestBody Reviews review){
		restTemplate.postForObject(url + "/addreview", review, String.class);
		return new ResponseEntity<>("{\"message\" : \"Review published\"}",HttpStatus.OK);
	}
}
