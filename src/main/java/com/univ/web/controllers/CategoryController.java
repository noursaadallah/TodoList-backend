package com.univ.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.univ.dal.CategoryRepository;
import com.univ.model.Category;
import com.univ.web.interfaces.ICategoryController;

@Controller
public class CategoryController implements ICategoryController {

	CategoryRepository cr = new CategoryRepository();
	
	public List<Category> getCategories(){
		return cr.getAllCategories();
	}
	
	public Category getCategoryById(@PathVariable("id") long id ){
		return cr.getCategoryById(id);
	}
	
	
	public Category createCategory(@RequestBody Category c){
		return cr.createCategory(c);
	}
	
	
	public void deleteCategory(@PathVariable("id") long id){
		cr.removeCategory(id);
	}
	

	public Category updateCategory(@PathVariable("id") long id, @RequestBody Category c){
		return cr.updateCategory(id, c);
	}
}
