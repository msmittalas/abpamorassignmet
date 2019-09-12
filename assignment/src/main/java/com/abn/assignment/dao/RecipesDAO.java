package com.abn.assignment.dao;

import java.util.List;

import com.abn.assignment.model.RecipeDataBean;

/**RecipesDAO.java RecipesDAO  interface 
 * @author MITTAL
 *
 */
public interface RecipesDAO {

	public void insertRecipes(RecipeDataBean bean,String user) throws Exception;
	public void removeRecipes(RecipeDataBean bean,String user)throws Exception;
	public void updateRecipes(RecipeDataBean bean,String user)throws Exception;
	public List<RecipeDataBean> getAll()throws Exception;
	public RecipeDataBean getRecipe(RecipeDataBean bean)throws Exception;
	
}
