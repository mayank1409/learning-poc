package io.javamasters.spring.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import io.javamasters.spring.web.persistence.model.Todo;
import io.javamasters.spring.web.persistence.model.User;
import io.javamasters.spring.web.service.ITodoService;
import io.javamasters.spring.web.service.IUserService;

@Controller
@SessionAttributes("email")
public class TodoController {

	@Autowired
	private ITodoService todoService;

	@Autowired
	private IUserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/todos")
	public String getTodos(ModelMap model) {
		User user = userService.findByEmail(getLoggedInUserName(model));
		List<Todo> todos = todoService.retrieveTodo(user);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@GetMapping("/add-todo")
	public String addTodo(ModelMap model) {
		model.addAttribute("todo",
				new Todo(0, userService.findByEmail(getLoggedInUserName(model)), "Default Desc", false, new Date()));
		return "todo";
	}

	@PostMapping("/add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		todoService.addTodo(userService.findByEmail(getLoggedInUserName(model)), todo);
		return "redirect:/todos";
	}

	@GetMapping("/update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	@PostMapping("/update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser(userService.findByEmail(getLoggedInUserName(model)));
		todoService.updateTodo(todo);
		return "redirect:/todos";
	}

	@GetMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/todos";
	}

	private String getLoggedInUserName(ModelMap modelMap) {
		return (String) modelMap.get("email");
	}

}
