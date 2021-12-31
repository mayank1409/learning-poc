package io.javamasters.spring.web.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javamasters.spring.web.persistence.model.Todo;
import io.javamasters.spring.web.persistence.model.User;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(User user);
}