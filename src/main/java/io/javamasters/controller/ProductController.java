package io.javamasters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javamasters.entity.Product;
import io.javamasters.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping()
	public ResponseEntity<Product> save(@RequestBody Product product) {
		product = productRepository.save(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
}
