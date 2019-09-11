package nl.abp.assignment.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import nl.abp.assignment.AssignmentProjectConstant;
import nl.abp.assignment.dao.RecipesDAO;
import nl.abp.assignment.dao.derbyImpl.RecipesDAODerbyImpl;
import nl.abp.assignment.model.RecipeDataBean;
import nl.abp.assignment.service.RecipesService;

/**RecipesServiceDirectImpl.java implements RecipeService and acts bridge between endpoint and dao class
 * @author MITTAL
 * 
 *
 */
public class RecipesServiceDirectImpl  implements RecipesService{
	RecipesDAO dao;
	final static Logger logger=Logger.getLogger(RecipesServiceDirectImpl.class);
	
	public RecipesServiceDirectImpl()throws Exception {
		try
		{
			if(AssignmentProjectConstant.CURRENT_DBTYPE.equals(AssignmentProjectConstant.DERBY_DBTYPE))
			{	
				this.dao=new RecipesDAODerbyImpl();
			}
			
		}
		catch(Exception e)
		{
			logger.error("Error While getting DAO connection"+e.getMessage());
			throw e;
		}
	}
	/**
	 * calls insert method on Dao layer
	 */
	public void addRecipe(RecipeDataBean bean, String userId) throws Exception {
	
		dao.insertRecipes(bean,userId);

	}
	/**
	 * calls remove method on Dao layer
	 */
	public void removeRecipe(RecipeDataBean bean, String userId) throws Exception {

		dao.removeRecipes(bean, userId);
	}
	/**
	 * calls getAll method on Dao layer
	 */
	public List<RecipeDataBean> geAllRecipes() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAll();
	}
	
	/**
	 * calls edit method on Dao layer
	 */

	public void editRecipe(RecipeDataBean bean, String userid) throws Exception {
		
		dao.updateRecipes(bean, userid);
	}
	/**
	 * calls getRecipe method on Dao layer
	 */
	public RecipeDataBean getRecipe(RecipeDataBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.getRecipe(bean);
	}

}
