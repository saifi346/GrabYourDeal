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

import com.deal.model.Cart;


@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {
	
	@Value("${cart.URL}")
	String url;

    RestTemplate rest = new RestTemplate();
	
	@GetMapping("/usercart/{username}")
	public ResponseEntity<Cart> getUserCart(@PathVariable("username") String username) {
		Cart cart =rest.getForObject(url + "/usercart/" + username, Cart.class);
		return new ResponseEntity<>(cart, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addtocart")
	public ResponseEntity<?> addProductsToCart(@RequestBody Cart cart){
		String msg = rest.postForObject(url + "/addtocart", cart, String.class);
		return new ResponseEntity<>("{\"message\" : \"Product added to cart\"}",HttpStatus.OK);
	}
	
	@PutMapping("/updatecart")
	public ResponseEntity<?> updateCart(@RequestBody Cart cart){
		rest.put(url + "/updatecart", cart);
		return new ResponseEntity<>("{\"message\" : \"cart updated\"}",HttpStatus.OK);
	}
	
	

}
