package com.deal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.deal.model.Order;
import com.deal.model.OrderProduct;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Value("${order.URL}")
	String url;
	
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/getallorders/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Order> getAllOrders(@PathVariable("username") String username) {
		Order orders = restTemplate.getForObject(url + "/getallorders/"+username, Order.class);
		return new ResponseEntity<>(orders, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/placeorder")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> placeOrder(@RequestBody Order order) {
		restTemplate.postForObject(url + "/placeorder", order, String.class);
		return new ResponseEntity<>("{\"message\" : \"Order placed\"}", HttpStatus.OK);
	}

	@PutMapping("/updatestatus/{username}/{orderId}")
	public ResponseEntity<?> updateOrderStatus(@PathVariable("username") String username,
			@PathVariable("orderId") String orderId, @RequestParam("status") String status) {
		restTemplate.put(url + "/updatestatus/" + username + "/" + orderId+"?status="+status,1);
		return new ResponseEntity<>("{\"message\" : \"status updated\"}", HttpStatus.OK);
	}

	@GetMapping("/getordersbystatus/{status}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<OrderProduct[]> getOrders(@PathVariable("status") String status) {
		OrderProduct orders[] = restTemplate.getForObject(url + "/getordersbystatus/"+status, OrderProduct[].class);
		return new ResponseEntity<>(orders, new HttpHeaders(), HttpStatus.OK);
	}

}
