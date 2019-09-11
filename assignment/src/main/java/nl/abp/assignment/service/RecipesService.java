package nl.abp.assignment.service;

import java.util.List;

import nl.abp.assignment.model.RecipeDataBean;

public interface RecipesService {

	public void addRecipe(RecipeDataBean bean,String userId) throws Exception;
	public void removeRecipe(RecipeDataBean bean,String userId) throws Exception;
	public List<RecipeDataBean> geAllRecipes() throws Exception;
	public void editRecipe(RecipeDataBean bean,String userid)throws Exception;
	public RecipeDataBean getRecipe(RecipeDataBean bean)throws Exception;
}
