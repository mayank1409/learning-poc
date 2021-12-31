package io.javamasters.spring.web.service;

import java.util.List;

import io.javamasters.spring.web.persistence.model.Todo;
import io.javamasters.spring.web.persistence.model.User;

public interface ITodoService {

	void addTodo(User user, Todo todo);

	void updateTodo(Todo todo);

	void deleteTodo(int id);

	Todo retrieveTodo(int id);

	List<Todo> retrieveTodo(User user);
	
	

}
