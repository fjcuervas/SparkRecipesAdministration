package com.spark.dao;

import java.util.List;

import com.spark.model.Recipe;

/**
 * Interface with the possible actions to perform in the database (insert, edit, delete, list)
 * These methods will be implemented in the RecipeDAOImpl java class 
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 * */
public interface IRecipeDAO {
	
	public boolean insertRecipe(Recipe recipe);

	public boolean deleteRecipe(String id);

	public boolean updateRecipe(Recipe recipe);

	public List<Recipe> recipeList();
	
	public Recipe findRecipe(String id);
}
