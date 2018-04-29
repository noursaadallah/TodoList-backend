package com.univ.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main 
{
	public static void main( String[] args )
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
		EntityManager entityManager = emf.createEntityManager();

		EntityTransaction tx = entityManager.getTransaction();

		try{

			tx.begin();

			Todo t1 = new Todo();
			t1.setTitle("my first todo");
			t1.setComplete(true);
			
			Todo t2 = new Todo();
			t2.setTitle("my second todo");
			
			Todo t3 = new Todo();
			t3.setTitle("my third todo");
			
			Category c1 = new Category();
			c1.setName("Today");
			
			Category c2 = new Category();
			c2.setName("Tomorrow");
			
			t1.setCategory(c1);
			t2.setCategory(c1);
			t3.setCategory(c2);
			
			c1.addTodo(t1); c1.addTodo(t2);
			c2.addTodo(t3);
			
			entityManager.persist(t1);
			entityManager.persist(t2);
			entityManager.persist(t3);
			entityManager.persist(c1);
			entityManager.persist(c2);

			tx.commit();			

		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}

	}
}
