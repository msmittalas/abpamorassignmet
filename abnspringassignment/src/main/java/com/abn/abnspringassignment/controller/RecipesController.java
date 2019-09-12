package com.abn.abnspringassignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abn.abnspringassignment.entity.RecipesDataBean;
import com.abn.abnspringassignment.exception.RecipesException;
import com.abn.abnspringassignment.model.RecipeResponse;
import com.abn.abnspringassignment.service.RecipesServices;
import com.abn.abnspringassignment.util.PayloadValidator;

@RestController
public class RecipesController {
	private static final Logger logger = LoggerFactory.getLogger(RecipesController.class);
	
	@Autowired
	RecipesServices recipesService;
	
	@RequestMapping(value="/recipes", method=RequestMethod.GET)
	public ResponseEntity<List<RecipesDataBean>> getAllRecipes()
	{
		return new ResponseEntity<List<RecipesDataBean>>(recipesService.geAllRecipes(), HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
	public ResponseEntity<RecipesDataBean> getRecipeById(@PathVariable("id") long id) throws RecipesException{
	RecipesDataBean recipesDataBean  = recipesService.getRecipeById(id);
		if (recipesDataBean == null || recipesDataBean.getRecipeId() <= 0){
	            throw new RecipesException("Recipe doesn´t exist");
	    	}
			return new ResponseEntity<RecipesDataBean>(recipesDataBean, HttpStatus.OK);
		}
	 
	 @RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
			public ResponseEntity<RecipeResponse> removeRecipeById(@PathVariable("id") long id) throws RecipesException{
		 	RecipesDataBean recipesDataBean  = recipesService.getRecipeById(id);
			if (recipesDataBean == null || recipesDataBean.getRecipeId() <= 0){
		            throw new RecipesException("Recipe doesn´t exist");
		    	}
			recipesService.removeRecipe(recipesDataBean);
			return new ResponseEntity<RecipeResponse>(new RecipeResponse(HttpStatus.OK.value(), "Recipe has been deleted"), HttpStatus.OK);
		}
	 @RequestMapping(value = "/recipes", method = RequestMethod.POST)
	   	public ResponseEntity<RecipesDataBean> saveRecipe(@RequestBody RecipesDataBean payload) throws RecipesException{
	    	if (!PayloadValidator.validateCreatePayload(payload)){
	            throw new RecipesException("Payload malformed, id must not be defined");
	    	}
			return new ResponseEntity<RecipesDataBean>(recipesService.addOrUpdateRecipe(payload), HttpStatus.OK);
	   	}
	 
	  @RequestMapping(value = "/recipes", method = RequestMethod.PATCH)
	   	public ResponseEntity<RecipesDataBean>  updateRecipe(@RequestBody RecipesDataBean payload) throws RecipesException{
		  RecipesDataBean recipesDataBean  = recipesService.getRecipeById(payload.getRecipeId());
			if (recipesDataBean == null || recipesDataBean.getRecipeId() <= 0){
		            throw new RecipesException("Recipe doesn´t exist");}
			recipesDataBean.setCreatedBy(payload.getCreatedBy());
			recipesDataBean.setCreatedDate(payload.getCreatedDate());
			recipesDataBean.setIsVeg(payload.getIsVeg());
			recipesDataBean.setNumberOfPeople(payload.getNumberOfPeople());
			recipesDataBean.setIngredients(payload.getIngredients());
			recipesDataBean.setInstructions((payload.getInstructions()));
			recipesDataBean.setRecipeName(payload.getRecipeName());
			recipesDataBean.setUpdatedBy(payload.getUpdatedBy());
			recipesDataBean.setUpdatedDate(payload.getUpdatedDate());
			return new ResponseEntity<RecipesDataBean>(recipesService.addOrUpdateRecipe(recipesDataBean), HttpStatus.OK);
	   	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
