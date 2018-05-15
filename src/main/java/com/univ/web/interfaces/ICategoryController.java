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

import com.univ.model.Category;

@Controller
@RequestMapping("categories")
@CrossOrigin(origins = "http://localhost:4200")
public interface ICategoryController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody 
	public List<Category> getCategories();
	
	@RequestMapping( value="/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody 
	public Category getCategoryById(@PathVariable("id") long id );
	
	@RequestMapping(method = RequestMethod.POST , consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category createCategory(@RequestBody Category c);
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCategory(@PathVariable("id") long id);
	
	@RequestMapping(value="/{id}" , method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Category updateCategory(@PathVariable("id") long id, @RequestBody Category c);
	
}
