package io.javamasters.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.javamasters.ProductdataApplicationTests;
import io.javamasters.entity.Product;

public class ProductRepositoryTest extends ProductdataApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testCreate() {
		Product product = new Product();
		product.setName("IPhone");
		product.setDesc("Awesome");
		product.setPrice(1000.0);
		product = productRepository.save(product);

		assertNotNull(product.getId());
	}
	
	@Test
	public void testRead() {
		Product product = new Product();
		product.setName("MacBook");
		product.setDesc("Awesome");
		product.setPrice(10000.0);
		product = productRepository.save(product);
		
		Optional<Product> optionalProduct = productRepository.findById(product.getId());
		
		assertNotNull(optionalProduct.get());
		assertEquals("MacBook" , optionalProduct.get().getName());

	}
	
	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setName("IPad");
		product.setDesc("Awesome");
		product.setPrice(100.0);
		product = productRepository.save(product);
		
		Optional<Product> optionalProduct = productRepository.findById(product.getId());
		
		Product product2 = optionalProduct.get();

		product2.setPrice(150.0);
		product2 = productRepository.save(product2);
		
		assertNotNull(product2);
		assertEquals(150.0 , product2.getPrice());

	}
}
