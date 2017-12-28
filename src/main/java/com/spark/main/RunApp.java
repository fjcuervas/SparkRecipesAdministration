package com.spark.main;

import com.spark.controller.RecipeController;

/**
 * Main class that start a web application with a small administration 
 * of recipes (add, delete, edit and list) using spark framework, 
 * HyperSQL DataBase as relational database software written in Java
 * and FreeMarker template engine to generate text output
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 * */
public class RunApp {

	public static void main(String[] args) {

		RecipeController recipeController = new RecipeController();			

		recipeController.manageRecipe();
	}

}
