package com.spark.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.spark.dao.RecipeDAOImpl;
import com.spark.model.Recipe;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * Class controller to create a template based on the action (insert, edit, delete, list)
 * The template will be returned to be filled with the information of the recipe
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 */
public class FreeMakerTemplateController {
	
	private final String EDIT_RECIPE = "/editRecipe.ftl";
	private final String DELETE_RECIPE = "/index.ftl";
	private final String INSERT_RECIPE = "/insertRecipe.ftl";
	private final String ERROR_TRANSACTION = "/errorTransaction.xhtml";
	private final String RECIPE_LIST = "/index.ftl";
	
	private String templateName;
    private Configuration configuration;
    private Template template;

    public Writer generateTemplate(String action, String id){
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	
    	RecipeDAOImpl recipeDAO = new RecipeDAOImpl();
    	
    	if(action.equals("edit")){
    		
    		templateName = EDIT_RECIPE;
    		
    		Recipe recipe = recipeDAO.findRecipe(id);
    		
    		parameters.put("recipe", recipe);
    		
    	} else if(action.equals("delete")){
    		
    		templateName = DELETE_RECIPE;
    		
    	} else if(action.equals("insert")){
    		
    		templateName = INSERT_RECIPE;
    		
    	}  else if(action.equals("error")){
    		
    		templateName = ERROR_TRANSACTION;
    	
    	} else {
    		
            List<Recipe> recipeList = recipeDAO.recipeList();
            
            parameters.put("recipes", recipeList);
            
    		templateName = RECIPE_LIST;
    	}
    	
        try {
        	
        	template = configuration.getTemplate(templateName);
        	
            Writer out = new StringWriter();
            
            template.process(parameters, out);
            
            return out;
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        } catch (TemplateException e) {
        	
            e.printStackTrace();
        }
        
		return null;
    }

	public FreeMakerTemplateController() {
		
        configuration = new Configuration(new Version(2, 3, 27));
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);
	}

}