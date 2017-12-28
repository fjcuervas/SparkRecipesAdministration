package com.spark.model;

/**
 * Model class that represent the recipe object with
 * 3 attributes (id, name and description)
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 * */
public class Recipe {

	private String id;
	private String description;
	private String name;
	
	public Recipe() {
	}
	public Recipe(String id, String description, String name) {
		
		this.id = id;
		this.description = description;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
