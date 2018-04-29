package com.univ.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import com.univ.model.Category;
import com.univ.model.Todo;

public class TodoRepository {

	protected UnitOfWork _uow;
	
	public TodoRepository() {
		super();
		this._uow = UnitOfWork.getUnitOfWork();
	}
	
	public List<Todo> getAllTodos() {
		List<Todo> res = _uow.em.createQuery("SELECT t FROM Todo t").getResultList();
		return res;
	}
	
	public List<Todo> getTodosByCategoryId(long id) {
		Category c = _uow.em.find(Category.class , id);
		List<Todo> res = new ArrayList<Todo>( c.getTodos());
		return res;
	}
	
	public Todo getTodoById(long id) {
		Todo t = _uow.em.find(Todo.class , id);
		return t;
	}
	
	public void createTodo(Todo t , long CategoryId) {
		Category c = _uow.em.find(Category.class , CategoryId);
		c.addTodo(t);
		t.setCategory(c);
		
		EntityTransaction tx = _uow.em.getTransaction();
		try {
			tx.begin();
			_uow.em.merge(c);
			_uow.em.persist(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public Todo updateTodo(long id , Todo update) {
		Todo origin = _uow.em.getReference(Todo.class , id);
		origin.setTitle(update.getTitle());
		origin.setComplete(update.isComplete());
		//origin.setCategory(update.getCategory());
		
		EntityTransaction tx = _uow.em.getTransaction();
		try {
		tx.begin();
		_uow.em.merge(origin);
		tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return origin;
	}
	
	public void deleteTodo(long id) {
		Todo t = _uow.em.getReference(Todo.class , id);
		Category c = _uow.em.getReference(Category.class , t.getCategory().getId());
		
		c.removeTodo(t);
		EntityTransaction tx = _uow.em.getTransaction();
		try {
		tx.begin();
		_uow.em.remove(t);
		_uow.em.merge(c);
		tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public void toggleTodoComplete(long id) {
		Todo origin = _uow.em.getReference(Todo.class , id);
		origin.setComplete(!origin.isComplete());
		
		EntityTransaction tx = _uow.em.getTransaction();
		try {
		tx.begin();
		_uow.em.merge(origin);
		tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
}
