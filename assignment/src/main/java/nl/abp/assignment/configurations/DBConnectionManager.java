package nl.abp.assignment.configurations;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import nl.abp.assignment.AssignmentProjectConstant;


/**
 * DBConnectionManager.java class to create  Derby Connection related objects 
 * @author MITTAL
 * 
 *
 */
public class DBConnectionManager {

	private  static Connection connection;
	private static  DBConnectionManager manager;
	private static String derbyURL= "jdbc:derby:c:\\MyDB\\recipes;create=true;";
	final static Logger logger=Logger.getLogger(DBConnectionManager.class);
	 
	private  DBConnectionManager(String dbType) throws Exception
	{
		if(dbType.equalsIgnoreCase(AssignmentProjectConstant.DERBY_DBTYPE))
		{
			initDerbyConnection();
		}
//		else
//		{
//			HibernateUtil.getSessionFactory();
//		}
	}
	/**static method to return getConnectionManager 
	 * @param dbType  
	 * @return
	 * @throws Exception
	 */
	public static DBConnectionManager getConnectionManager(String dbType)throws Exception
	{
		if(manager==null)
		{
			manager=new DBConnectionManager(dbType);
		}
		return manager;
	}
	
	/**this method is use to initialize Derby DB 
	 * @throws Exception
	 */
	private void initDerbyConnection()throws Exception
	{
		try {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		connection=DriverManager.getConnection(derbyURL);
		  Statement stmt = connection.createStatement();
		try{
		  stmt.executeUpdate("Create table recipes (recipeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), createdDate TIMESTAMP, isVeg char(10),numberOfPeople int ,ingredients LONG VARCHAR,instructions LONG VARCHAR,createdBy varchar(30),updatedBy varchar(30), updatedDate TIMESTAMP,recipeName varchar(30) ) ");
		}
		catch(Exception e)
		{
		logger.error("exception while creating table"+e);
		}

		}
		catch(ClassNotFoundException classExp)
		{
			logger.error("Error while getting DB connection "+classExp.getMessage());
			throw classExp;
		}
		catch(SQLException sqlExc )
		{
			logger.error("ERROR WHILE Getting DB COnnection "+sqlExc.getMessage());
			throw sqlExc;
		}
		
		  
	}
	/** method to get Connection 
	 * @return connection object 
	 */
	public  Connection getConnection() 
	{
		return connection;
	}
	
	/*
	public static void main(String []args)throws Exception {
		DBConnectionManager manager=DBConnectionManager.getConnectionManager("derby");
	}*/
	
}
