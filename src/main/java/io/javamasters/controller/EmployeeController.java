package io.javamasters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javamasters.entity.Employee;
import io.javamasters.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		employee = employeeRepository.save(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

}
