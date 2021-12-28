package io.javamasters.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javamasters.ProductdataApplicationTests;
import io.javamasters.entity.Product;

@AutoConfigureMockMvc
public class ProductControllerTest extends ProductdataApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testSaveProduct() throws JsonProcessingException, Exception {

		Product product = createProductData();

		mockMvc.perform(post("/products").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(product))).andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists());
	}

	private Product createProductData() {
		Product product = new Product();
		product.setName("Samsung Galaxy");
		product.setDesc("Awesome");
		product.setPrice(120.0);
		return product;
	}

}
