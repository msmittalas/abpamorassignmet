package nl.abp.assignment.dao.derbyImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import nl.abp.assignment.AssignmentProjectConstant;
import nl.abp.assignment.configurations.DBConnectionManager;
import nl.abp.assignment.dao.RecipesDAO;
import nl.abp.assignment.model.RecipeDataBean;
import nl.abp.assignment.util.DateConversion;

/**RecipesDAODerbyImpl.java Derby implementation of RecipesDAO interface 
 * @author MITTAL
 *
 */
public class RecipesDAODerbyImpl implements RecipesDAO {
	
	final static Logger logger=Logger.getLogger(RecipesDAODerbyImpl.class);
	Connection connection; 
	
	public RecipesDAODerbyImpl() throws Exception {
	
		connection=DBConnectionManager.getConnectionManager(AssignmentProjectConstant.DERBY_DBTYPE).getConnection();
	}
	
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	/**
	 *Method to insert Recipe in Derby Database
	 */
	public void insertRecipes(RecipeDataBean bean,String user) throws Exception {
		
		  try {
			  PreparedStatement prepStmt=connection.prepareStatement("insert into recipes (createdDate,isVeg,numberOfPeople,ingredients,instructions,createdBy,updatedBy,recipeName) values(?,?,?,?,?,?,?,?)");
			  prepStmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			  prepStmt.setString(2, bean.getIsVeg());
			  prepStmt.setInt(3, bean.getNumberOfPeople());
			  prepStmt.setString(4, bean.getIngredients());
			  prepStmt.setString(5, bean.getInstructions());
			  prepStmt.setString(6, user);
			  prepStmt.setString(7, "");
			  prepStmt.setString(8, bean.getRecipeName());
							  
			  prepStmt.executeUpdate();
			  
		} catch (SQLException e) {
			
			logger.error("ERROR WHILE INSERTING recipe data :"+e.getMessage());
			throw e;
		}
	}

	/**
	 *method to remove recipe from Database 
	 */
	public void removeRecipes(RecipeDataBean bean,String user) throws Exception {
		  try {
			  PreparedStatement prepStmt=connection.prepareStatement("delete from recipes where recipeId=? ");
			  prepStmt.setInt(1, bean.getRecipeId());
			  prepStmt.executeUpdate();
			  
		} catch (SQLException e) {
			
			logger.error("ERROR WHILE DELETING recipe data :"+e.getMessage());
			throw  e;
		}
	}

	/**
	 *method to update Recipe in Database
	 */
	public void updateRecipes(RecipeDataBean bean,String user) throws Exception {
		  try {
			  PreparedStatement prepStmt=connection.prepareStatement("update  recipes set isVeg=? ,numberOfPeople=?,ingredients=?,instructions=? ,updatedBy=? ,recipeName=?,updatedDate=? where recipeId=? ");
			  prepStmt.setString(1, bean.getIsVeg());
			  prepStmt.setInt(2,bean.getNumberOfPeople());
			  prepStmt.setString(3, bean.getIngredients());
			  prepStmt.setString(4,bean.getInstructions());
			  prepStmt.setString(5, user);
			  prepStmt.setString(6, bean.getRecipeName());
			  prepStmt.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
			  prepStmt.setInt(8, bean.getRecipeId());
			  prepStmt.executeUpdate();
			  
		} catch (SQLException e) {
			
			logger.error("ERROR WHILE UPDATING recipe data :"+e.getMessage());
			throw e;
		}	
	}

	public List<RecipeDataBean> getAll() {
		List<RecipeDataBean> beans=new ArrayList<RecipeDataBean>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM recipes");
			while(rs.next())
			{
				RecipeDataBean bean=popluateBeanFromResultSet(rs);
				beans.add(bean);
				
			}
		} catch (SQLException e) {
			logger.error("ERROR WHILE GETTING ALL recipes data :"+e.getMessage());
		}
		
		return beans;
	}
	
	/**
	 * method to read the resultset and map it to RecipeDataBean 
	 * @param rs ResultSet
	 * @return RecipeDataBean object
	 * @See RecipeDataBean
	 */
	public RecipeDataBean popluateBeanFromResultSet(ResultSet rs)throws SQLException
	{
		RecipeDataBean bean=new RecipeDataBean();
		bean.setRecipeId(rs.getInt("recipeId"));
		bean.setCreatedDate(DateConversion.toString(rs.getTimestamp("createdDate")));
		bean.setIsVeg(rs.getString("isVeg"));
		bean.setNumberOfPeople(rs.getInt("numberOfPeople"));
		bean.setIngredients(rs.getString("ingredients"));
		bean.setInstructions(rs.getString("instructions"));
		bean.setRecipeName(rs.getString("recipeName"));
		bean.setCreatedBy(rs.getString("createdBy"));
		bean.setUpdatedBy(rs.getString("updatedBy"));
		
		return bean;
	}


	/**
	 *method to get Recipe Object From Database
	 *@return RecipeDataBean Object
	 *@see RecipeDataBean
	 */
	public RecipeDataBean getRecipe(RecipeDataBean bean) throws Exception {
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM recipes where recipeId="+bean.getRecipeId());
			if(rs.next())
			{
				 bean=popluateBeanFromResultSet(rs);
				
			}
		} catch (SQLException e) {
		
		logger.error("ERROR WHILE GETTING SINGLE RECipe from DB"+e);
		throw e;
		}
		
		return bean;
	}

}
