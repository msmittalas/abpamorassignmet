package com.abn.assignment.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.abn.assignment.model.RecipeDataBean;
import com.abn.assignment.model.ServiceResponseDataBean;
import com.abn.assignment.service.RecipesService;
import com.abn.assignment.service.impl.RecipesServiceDirectImpl;
import com.fasterxml.jackson.annotation.JsonInclude;

/**RecipesCRUDRestResource.java EndPoint entry points CRUD Operation REST Entry Point
 * @author MITTAL
 * 
 */

@Path("/recipes")
public class RecipesCRUDRestResource {

	RecipesService recipesService;
	final static Logger logger=Logger.getLogger(RecipesCRUDRestResource.class);
	ObjectMapper MAPPER=new ObjectMapper();
	
	
	public RecipesCRUDRestResource() {
		try {
			recipesService=new RecipesServiceDirectImpl();
			MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		} catch (Exception e) {
			logger.error("unbale to create Service object"+e);
		}
	}
	
	/**
	 * EntryPoint Method to add Recipe , it will call service layer to add the data 
	 * @param bean  takes RecipeBean data in Json Format 
	 * @param userId  userId of logged in user  
	 * @return String  A String version of ServiceResponseDataBean 
	 * @see ServiceResponseDataBean
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addRecipes(RecipeDataBean bean,@HeaderParam("userId")String userId)
	{
		ServiceResponseDataBean responseBean=new ServiceResponseDataBean();
		String response="";
		try {
		recipesService.addRecipe(bean, userId);
		responseBean.setStatus("SUCCESS");
		}
		catch(Exception e)
		{
			logger.error("Error while calling RecipesService "+e.getMessage());
			responseBean.setStatus("ERROR");
			responseBean.setMessage("Error While Adding Recipe");
		}
		try {
		response=MAPPER.writeValueAsString(responseBean);
		}
		catch(Exception e)
		{
		response = "error";	
		}
		return response;
	}
	
	/**
	 * EntryPoint Method to return All the recipes from Service layer
	 * @return String  A String version of ServiceResponseDataBean
	 * @see ServiceResponseDataBean
	 */
	@GET
	@Path("/all")
	public String getRecipes()
	{
		ServiceResponseDataBean responseBean=new ServiceResponseDataBean();
		String response="";
		try 
		{
			List<RecipeDataBean> allRecords=recipesService.geAllRecipes();
			responseBean.setData(allRecords);
			responseBean.setMessage("SUCCESS");
			responseBean.setStatus("SUCCESS");
		}
		catch(Exception e)
		{
			logger.error("Error while calling RecipesService getALl  "+e.getMessage());
			responseBean.setStatus("ERROR");
			responseBean.setMessage("Error While getting Recipes");
		}
		try {
			response=MAPPER.writeValueAsString(responseBean);
			}
			catch(Exception e)
			{
			response = "error";	
			}
		
		return response;
	}
	/**EntryPoint Method to return single recipe from Service layer 
	 * 
	 * @param id  recipe id 
	 * @param userId requesting user
	 * @return JSON format of ServiceResponseDataBean
	 * @see ServiceResponseDataBean
	 */
	@GET
	@Path("/{id}")
	public String getRecipe(@PathParam("id")Integer id,@HeaderParam("userId")String userId)
	{
		ServiceResponseDataBean responseBean=new ServiceResponseDataBean();
		String response="";
		try 
		{
			RecipeDataBean recipeBean=new RecipeDataBean();
			recipeBean.setRecipeId(id);
			recipeBean=recipesService.getRecipe(recipeBean);
			List<RecipeDataBean> allRecords=new ArrayList<RecipeDataBean>();
			allRecords.add(recipeBean);
			responseBean.setData(allRecords);
			responseBean.setMessage("SUCCESS");
			responseBean.setStatus("SUCCESS");
		}
		catch(Exception e)
		{
			logger.error("Error while calling RecipesService getALl  "+e.getMessage());
			responseBean.setStatus("ERROR");
			responseBean.setMessage("Error While getting Recipes");
		}
		try {
			response=MAPPER.writeValueAsString(responseBean);
			}
			catch(Exception e)
			{
			response = "error";	
			}
		
		return response;
	}
	

	/** Entrypoint method to delete Recipe  from Service Layer
	 * @param id  recipeId of Recipe
	 * @param userId    userId of logged in user  
	 * @return  A String version of ServiceResponseDataBean
	 * @see ServiceResponseDataBean
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteRecipe(@PathParam("id")Integer id,@HeaderParam("userId")String userId)
	{
		String response="";
		ServiceResponseDataBean responseBean=new ServiceResponseDataBean();
		try {
			RecipeDataBean bean=new RecipeDataBean();
			bean.setRecipeId(id);
			recipesService.removeRecipe(bean, userId);
			responseBean.setMessage("Deleted recipe with id"+id);
			responseBean.setStatus("SUCCESS");
		}
		catch(Exception e)
		{
			logger.error("Error while calling RecipesService delete Recipe  "+e.getMessage());
			responseBean.setStatus("ERROR");
			responseBean.setMessage("Error While deleting  Recipe");
	
		}
		try {
			response=MAPPER.writeValueAsString(responseBean);
			}
			catch(Exception e)
			{
			response = "error";	
			}
			return response;
	}
	/**
	 * EntryPoint Method to update Recipe , it will call service layer to update the data 
	 * @param bean  takes RecipeBean data in Json Format 
	 * @param userId  userId of logged in user  
	 * @return String  A String version of ServiceResponseDataBean 
	 * @see ServiceResponseDataBean
	 */
	@PUT
	@Path("/update")
	public String updateRecipe(RecipeDataBean bean,@HeaderParam("userId")String userId)
	{

		ServiceResponseDataBean responseBean=new ServiceResponseDataBean();
		String response="";
		try {
		recipesService.editRecipe(bean, userId);
		responseBean.setStatus("SUCCESS");
		}
		catch(Exception e)
		{
			logger.error("Error while calling RecipesService "+e.getMessage());
			responseBean.setStatus("ERROR");
			responseBean.setMessage("Error While editing Recipe");
			
		}
		try {
		response=MAPPER.writeValueAsString(responseBean);
		}
		catch(Exception e)
		{
		response = "error";	
		}
		return response;
	
	}
	
}
