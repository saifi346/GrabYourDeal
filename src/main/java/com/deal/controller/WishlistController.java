package com.deal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.deal.model.Wishlist;
import com.deal.model.WishlistProduct;

@CrossOrigin("*")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Value("${wishlist.URL}")
	private String url;

	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/list/{username}")
	public ResponseEntity<Wishlist> getWishlist(@PathVariable("username") String username){
		Wishlist wlist = restTemplate.getForObject(url + "/list/" + username, Wishlist.class);
		return new ResponseEntity<>(wlist, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addtowishlist/{username}")
	public ResponseEntity<?> addProductToWishlist(@PathVariable("username") String username,@RequestBody WishlistProduct product){
		boolean flag = restTemplate.postForObject(url + "/addtowishlist/" + username, product,Boolean.class);
		if(flag) {
			return new ResponseEntity<>("{\"message\" : \"Product already added to wishlist\"}",HttpStatus.OK);
		}
		return new ResponseEntity<>("{\"message\" : \"Product added to list\"}",HttpStatus.OK);
	}
	
	@PutMapping("/removeproduct/{username}")
	public ResponseEntity<?> removeProductFromWishlist(@PathVariable("username") String username, @RequestBody WishlistProduct product){
		restTemplate.put(url + "/removeproduct/" + username, product);
		return new ResponseEntity<>("{\"message\" : \"Product removed from cart\"}",HttpStatus.OK);
	}
	
}
