package com.spark.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.spark.model.Recipe;

/**
 * Class that implements all methods to interact with the database.
 * These methods will let to execute DML actions on the database 
 * like insert, delete, update and select. 
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 * */
public class RecipeDAOImpl implements IRecipeDAO {
	
	private Connection connection;
	
	public RecipeDAOImpl() {
		connection = DBConnection.connect();
	}

	@Override
	public List<Recipe> recipeList() {
		
		List<Recipe> recipes = new ArrayList<>();

		try {
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT id, description, name FROM recipe ORDER BY id");
			
			while (resultSet.next()) {
				
				Recipe recipe = new Recipe();
				
				recipe.setId(resultSet.getString("id"));
				recipe.setDescription(resultSet.getString("description"));		
				recipe.setName(resultSet.getString("name"));	
				
				recipes.add(recipe);
			}
			
			resultSet.close();
			
			statement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return recipes;
	}
	
	@Override
	public Recipe findRecipe(String id) {
		
		Recipe recipe = new Recipe();

		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, description, name FROM recipe WHERE id = ?");
			
			preparedStatement.setString(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				recipe.setId(resultSet.getString("id"));
				recipe.setDescription(resultSet.getString("description"));		
				recipe.setName(resultSet.getString("name"));	
				
			}
			
			resultSet.close();
			
			preparedStatement.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return recipe;
	}
	
	@Override
	public boolean deleteRecipe(String id) {
		
			try {
				
				String sql = "DELETE FROM recipe WHERE id = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setString(1, id);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				return false;
			}
			
			return true;
		
	}

	@Override
	public boolean updateRecipe(Recipe recipe) {

		try {
			
			String sql = "UPDATE recipe SET description = ?, name = ? WHERE id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, recipe.getDescription());
			preparedStatement.setString(2, recipe.getName());	
			preparedStatement.setString(3, recipe.getId());	
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
							
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	@Override
	public boolean insertRecipe(Recipe recipe) {
		
		try {
			
			String sql = "INSERT INTO recipe (description, name) VALUES (?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
		
			String description = recipe.getDescription();
			String name = recipe.getName();
			
			statement.setString(1, description);
			statement.setString(2, name);

			statement.executeUpdate();
			statement.close();
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;

		}
	}
}
