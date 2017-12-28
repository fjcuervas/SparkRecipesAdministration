package com.spark.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spark.dao.RecipeDAOImpl;
import com.spark.model.Recipe;
import spark.Spark;

/**
 * Controller class to manage recipes based on the action (insert, edit, delete, list)
 * Depending on the action a corresponding web page will be displayed.
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 * */
public class RecipeController {

	
	private Writer out;
	
	private static final Logger LOG = LoggerFactory.getLogger("spark.Spark");
	
	RecipeDAOImpl recipeDAO = new RecipeDAOImpl();
	
	public void manageRecipe() {
        
		FreeMakerTemplateController fmt = new FreeMakerTemplateController();
		
		// Display a message error when not found handling
		Spark.notFound("<html><body><h1>The resource you are trying to see not exists</h1></body></html>");
		
		// Specify what happen when internal server error is thrown
		Spark.internalServerError("<html><body><h1>Internal server error - Custom 500 handling</h1></body></html>");
		
		// Specify what happen when initialization fails
		Spark.initExceptionHandler((e) -> {
			
			LOG.error("Ignite failed", e);
			
			System.exit(100);
		});
		
		//When the URL is root, the control is redirected to display all recipes
		get("/", (request, response) -> {
			
			response.redirect("/recipes");
			
			return null;
			
		});
		
		//The control is redirected to FreeMakerTemplateController to generate 
		//the template and display all recipes from the database
		get("/recipes", (request, response) -> {
			
			//Create a new session if not exists to manage the "transaction" attribute
			if(request.session().isNew()){
				
				request.session(true);
				
				request.session().attribute("transaction", "false");
			}
			
			out = fmt.generateTemplate("recipeList", "-1");
			
			return out.toString();
			
		});		
		
		//Display the recipe form to edit information
		get("/edit/:id", (request, response) -> {
			
			out = fmt.generateTemplate("edit", request.params(":id"));
			
			response.type("text/html");
			
			return out.toString();
		});
		
		//Take control when "save" button of the recipe form is pressed, 
		//insert a new recipe and redirect the control to display all recipes
		post("/edit", (request, response) -> {
			
			String recipeId = request.queryParams("recipeid");
			String description = request.queryParams("description");
			String name = request.queryParams("name");
			
			RecipeDAOImpl bDAO = new RecipeDAOImpl();
			
			Recipe recipe = new Recipe(recipeId, description, name);
			
			//handle situation of "double submission of forms". When "save" button
			//is pressed twice, its display a message and a button to return to all recipes
			if(request.session().attribute("transaction").equals("true")){
				
				request.session().attribute("transaction", "false");
				
				response.type("text/html");
				
				out = fmt.generateTemplate("error", "-1");
				
				return out.toString();
				
				
			} else if(bDAO.updateRecipe(recipe)){
				
				request.session().attribute("transaction", "true");
				
				out = fmt.generateTemplate("edit", recipeId);
				
				return out.toString();	
				
			} else {
				
				response.status(401);
				
				return "Error from updating...";
				
			}
		});
		
		//Take control when "delete" button from main form is pressed
		//and delete a recipe from the database
		get("/delete/:id", (request, response) -> {
			
			String recipeid = request.params(":id");
			
			RecipeDAOImpl bDAO = new RecipeDAOImpl();
			
			if(bDAO.deleteRecipe(recipeid)){
				
				response.redirect("/recipes");
				
				return null;	
				
			} else {
				
				response.status(401);
				
				return "Error from deleting...";
			}
		});
		
		//Display the form to insert a new recipe
		get("/insert", (request, response) -> {
			
			out = fmt.generateTemplate("insert", "-1");
			
			return out.toString();
			
		});
		
		//Take control when "save" button from "insert" form is pressed
		//and insert the new recipe to the database
		post("/insert", (request, response) -> {
			
			String description = request.queryParams("description");
			String name = request.queryParams("name");
			
			Recipe recipe = new Recipe("-1", description, name);
			
			RecipeDAOImpl bDAO = new RecipeDAOImpl();
			
			//handle situation of "double submission of forms". When "save" button
			//is pressed twice, its display a message and a button to return to all recipes			
			if(request.session().attribute("transaction").equals("true")){
				
				request.session().attribute("transaction", "false");
				
				response.type("text/html");
				
				out = fmt.generateTemplate("error", "-1");
				
				return out.toString();
				
			} else if(bDAO.insertRecipe(recipe)){
				
				response.redirect("/recipes");
				
				return null;	
				
			} else{
				
				response.status(401);
				
				return "Error from inserting...";
				
			}
		});

	}

}
