package com.univ.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.univ.web.interfaces.ITodoController;
import com.univ.dal.TodoRepository;
import com.univ.model.Todo;

@Controller
public class TodoController implements ITodoController {
	
	TodoRepository tr = new TodoRepository();
	
	public List<Todo> getAllTodos(){
		return tr.getAllTodos();
	}
	
	public Todo getTodoById(@PathVariable("id") long id){
		return tr.getTodoById(id);
	}
	

	public List<Todo> getTodosByCategoryId(@PathVariable("id") long id){
		return tr.getTodosByCategoryId(id);
	}
	

	public void createTodo(@PathVariable("categoryId") long categoryId , @RequestBody Todo t){
		tr.createTodo(t, categoryId);
	}
	

	public void updateTodo(@PathVariable("id") long id, @RequestBody Todo t){
		tr.updateTodo(id, t);
	}
	

	public void deleteTodo(@PathVariable("id") long id){
		tr.deleteTodo(id);
	}
	

	public void toggleTodoComplete( @PathVariable("id") long id ){
		tr.toggleTodoComplete(id);
	}
}
