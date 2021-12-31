package io.javamasters.spring.web.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javamasters.spring.web.persistence.dao.TodoRepository;
import io.javamasters.spring.web.persistence.model.Todo;
import io.javamasters.spring.web.persistence.model.User;
import io.javamasters.spring.web.service.ITodoService;

@Service
public class TodoServiceImpl implements ITodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public void addTodo(User user, Todo todo) {
		todo.setUser(user);
		todoRepository.save(todo);
	}

	@Override
	public void updateTodo(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(int id) {
		todoRepository.deleteById(id);
	}

	@Override
	public Todo retrieveTodo(int id) {
		Optional<Todo> optionalTodo = todoRepository.findById(id);
		if (optionalTodo.isPresent()) {
			return optionalTodo.get();
		}
		return null;
	}

	@Override
	public List<Todo> retrieveTodo(User user) {
		return todoRepository.findByUser(user);
	}

}