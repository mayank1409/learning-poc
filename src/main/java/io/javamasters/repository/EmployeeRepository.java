package io.javamasters.repository;

import org.springframework.data.repository.CrudRepository;

import io.javamasters.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
