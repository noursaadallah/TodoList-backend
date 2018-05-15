package com.univ.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.dal.CategoryRepository;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	protected String title;
	protected boolean complete;
	@ManyToOne
	protected Category category;
	
	public Todo() {
		super();
	}

//	@JsonCreator
//	public Todo( @JsonProperty("title") String title , 
//				@JsonProperty("complete") boolean complete) {
//		this.title = title;
//		this.complete = complete;
//	}
	
	@JsonCreator
	public Todo( @JsonProperty("title") String title , 
				@JsonProperty("complete") boolean complete,
				@JsonProperty("category") int catId ) {
		this.title = title;
		this.complete = complete;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Todo))return false;
	    Todo _other = (Todo)other;
	    if(_other.id == this.id) return true;
	    if( !_other.title.equals(this.title) ) return false;
	    return false;
	}
	
}
