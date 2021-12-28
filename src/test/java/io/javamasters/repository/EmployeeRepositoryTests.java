package io.javamasters.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.javamasters.ProductdataApplicationTests;
import io.javamasters.entity.Employee;

public class EmployeeRepositoryTests extends ProductdataApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testSave() {
		Employee employee = new Employee();
		employee.setName("Mayank Madhav");
		employee = employeeRepository.save(employee);
		
		assertNotNull(employee);
	}
	
	@Test
	public void testUpdate() {
		Employee employee = new Employee();
		employee.setName("Mayank Madhav");
		employee = employeeRepository.save(employee);
		
		Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
		Employee employee2 = optionalEmployee.get();
		
		employee2.setName("Mayank Srivastava");
		employee2 = employeeRepository.save(employee2);
		
		assertEquals("Mayank Srivastava", employee2.getName());
		
	}
	
	@Test
	public void testRead() {
		Employee employee = new Employee();
		employee.setName("Mayank Madhav");
		employee = employeeRepository.save(employee);
		
		Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
		Employee employee2 = optionalEmployee.get();
		assertNotNull(employee2);
	}
	
	@Test
	public void testReadAll() {
		Employee employee1 = new Employee();
		Employee employee2 = new Employee();
		
		employee1.setName("Mayank Madhav");
		employee2.setName("Mayank Srivastava");
		
		List<Employee> employees = List.of(employee1, employee2);
		employeeRepository.saveAll(employees);
		
		Iterable<Employee> employeeList = employeeRepository.findAll();
		assertNotNull(employeeList);
	}
}
