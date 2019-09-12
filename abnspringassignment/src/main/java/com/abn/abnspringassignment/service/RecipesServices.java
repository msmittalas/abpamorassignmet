package com.abn.abnspringassignment.service;

import java.util.List;

import com.abn.abnspringassignment.entity.RecipesDataBean;


public interface RecipesServices {


	public RecipesDataBean addOrUpdateRecipe(RecipesDataBean bean);
	public void removeRecipe(RecipesDataBean bean) ;
	public List<RecipesDataBean> geAllRecipes();
	public RecipesDataBean getRecipeById(long id);
}

