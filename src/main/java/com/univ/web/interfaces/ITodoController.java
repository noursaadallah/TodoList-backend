package com.univ.web.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.univ.model.Todo;

@Controller
@RequestMapping("todos")
@CrossOrigin(origins = "http://localhost:4200")
public interface ITodoController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody //return type is written to the HTTP response body (and not placed in a Model, or interpreted as a view name)
	public List<Todo> getAllTodos();
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo getTodoById(@PathVariable("id") long id);
	
	@RequestMapping(value = "/bycategory/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Todo> getTodosByCategoryId(@PathVariable("id") long id);
	
	@RequestMapping(value="/{categoryId}" , method = RequestMethod.POST , consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo createTodo(@PathVariable("categoryId") long categoryId , @RequestBody Todo t);
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.PUT , consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public void updateTodo(@PathVariable("id") long id, @RequestBody Todo t);
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteTodo(@PathVariable("id") long id);
	
	@RequestMapping(value="/togglecomplete/{id}",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo toggleTodoComplete( @PathVariable("id") long id );
}
