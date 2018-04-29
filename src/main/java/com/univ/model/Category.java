package com.univ.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	protected String name;
	@JsonIgnore
	@OneToMany(mappedBy = "category" , cascade=CascadeType.ALL)
	protected Collection<Todo> todos;
	
	public Category() {
		super();
		this.todos = new ArrayList<Todo>();
	}
	
	@JsonCreator
	public Category( @JsonProperty("name") String name ) {
		this.name = name;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Todo> getTodos() {
		return todos;
	}
	public void setTodos(Collection<Todo> todos) {
		this.todos = todos;
	}
	
	public void addTodo(Todo t) {
		this.todos.add(t);
	}
	
	public void removeTodo(Todo t) {
		this.todos.remove(t);
	}
	
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Category))return false;
	    Category _other = (Category)other;
	    if(_other.id == this.id) return true;
	    if( !_other.name.equals(this.name) ) return false;
	    return false;
	}
}
