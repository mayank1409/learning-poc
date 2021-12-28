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
import io.javamasters.entity.Employee;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends ProductdataApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testSaveEmployee() throws JsonProcessingException, Exception {
		Employee employee = createNewEmployee();

		mockMvc.perform(post("/employees").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee))).andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists());
	}

	private Employee createNewEmployee() {
		Employee employee = new Employee();
		employee.setName("Mayank Madhav");
		return employee;
	}
}
