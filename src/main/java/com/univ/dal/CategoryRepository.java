package com.univ.dal;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.univ.model.Category;
import com.univ.model.Todo;

public class CategoryRepository {

	protected UnitOfWork _uow;
	
	public CategoryRepository() {
		super();
		this._uow = UnitOfWork.getUnitOfWork();
	}
	
	public List<Category> getAllCategories() {
		List<Category> res = _uow.em.createQuery("SELECT u FROM Category u").getResultList();
		return res;
	}
	
	public Category createCategory(Category c) {
		EntityTransaction tx = _uow.em.getTransaction();
		try {
			tx.begin();
			_uow.em.persist(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return c;
	}
	
	public void removeCategory(long id) {
		Category c = _uow.em.getReference(Category.class, id);
		EntityTransaction tx = _uow.em.getTransaction();
		try {
			tx.begin();
			_uow.em.remove(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public Category updateCategory(long id , Category update) {
		Category origin = _uow.em.getReference(Category.class , id);
		origin.setName(update.getName());
		origin.setTodos(update.getTodos());
		
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
	
	public Category getCategoryById(long id) {
		Category c = _uow.em.find(Category.class , id);
		return c;
	}
}
