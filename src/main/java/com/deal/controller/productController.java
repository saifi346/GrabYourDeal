package com.deal.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.deal.model.Product;
import com.deal.payload.response.MessageResponse;

@RestController
@CrossOrigin("*")
public class productController {

	@Value("${products.URL}")
	String url;

	@Autowired
	RestTemplate restTemplate;

	/*
	 * products rest calls
	 */
	@GetMapping("/product/allProducts")
	public ResponseEntity<Product[]> getAllproducts() {
		Product[] products = restTemplate.getForObject(url, Product[].class);
        for(Product product : products) {
        	byte[] bytes = restTemplate.getForObject(url + "/retreive/" + product.getTitle(), byte[].class);
    		String encoded = Base64.getEncoder().encodeToString(bytes);
    		product.setEncodedImage(encoded);
        }
		return new ResponseEntity<>(products, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/product/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable("productName") String productName) {
		Product product = restTemplate.getForObject(url + "/product/" + productName, Product.class);
		byte[] bytes = restTemplate.getForObject(url + "/retreive/" + product.getTitle(), byte[].class);
		String encoded = Base64.getEncoder().encodeToString(bytes);
		product.setEncodedImage(encoded);
		return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/product/search/{productName}")
	public ResponseEntity<Product[]> searchProductsByName(@PathVariable("productName") String productName) {
		Product[] products = restTemplate.getForObject(url + "/product/search/" + productName, Product[].class);
		for(Product product : products) {
        	byte[] bytes = restTemplate.getForObject(url + "/retreive/" + product.getTitle(), byte[].class);
    		String encoded = Base64.getEncoder().encodeToString(bytes);
    		product.setEncodedImage(encoded);
        }
		return new ResponseEntity<>(products, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/product/category/{category}")
	public ResponseEntity<Product[]> adminAccess(@PathVariable("category") String category) {
		Product[] products = restTemplate.getForObject(url + "/category/" + category, Product[].class);
		for(Product product : products) {
        	byte[] bytes = restTemplate.getForObject(url + "/retreive/" + product.getTitle(), byte[].class);
    		String encoded = Base64.getEncoder().encodeToString(bytes);
    		product.setEncodedImage(encoded);
        }
		return new ResponseEntity<>(products, new HttpHeaders(), HttpStatus.OK);
	}

	/*
	 * @GetMapping("/product/photo/{name}") public @ResponseBody Map<String, String>
	 * getphoto(@PathVariable("name") String name) { byte[] bytes =
	 * restTemplate.getForObject(url + "/retreive/" + name, byte[].class); String
	 * encoded = Base64.getEncoder().encodeToString(bytes);
	 * 
	 * Map<String, String> jsonMap = new HashMap<>();
	 * 
	 * jsonMap.put("content", encoded);
	 * 
	 * return jsonMap; }
	 */

	
	@PostMapping("/product/publishProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		String msg = restTemplate.postForObject(url + "/publishProduct", product, String.class);
		return ResponseEntity.ok(new MessageResponse(msg));
	}
	
	@PostMapping("/product/publishProduct/photo")
	public ResponseEntity<?> savePhoto(@RequestBody MultipartFile file) {
		String msg = restTemplate.postForObject(url + "/upload", file,String.class);
		return ResponseEntity.ok(new MessageResponse(msg));
	}

	@PutMapping("/updateProduct/{productName}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateProduct(@PathVariable("productName") String productName,
			@RequestBody Product product) {
		restTemplate.put(url + "/updateProduct/" + productName, product);
		return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/updateProductStock/{productName}")
	public ResponseEntity<?> updateProductStock(@PathVariable("productName") String productName,
			@RequestBody Product product) {
		restTemplate.put(url + "/updateProductStock/" + productName, product);
		return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/product/deleteProduct/{productName}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProduct(@PathVariable("productName") String productName) {
		Product products = restTemplate.getForObject(url + "/product/" + productName, Product.class);
		restTemplate.delete(url + "/deleteProduct/" + productName);
		restTemplate.delete(url + "/delete/" + products.getTitle());
		return ResponseEntity.ok("product deleted successfully");
	}

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

}
