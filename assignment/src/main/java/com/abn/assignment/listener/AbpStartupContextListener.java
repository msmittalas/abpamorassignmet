package com.abn.assignment.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.abn.assignment.AssignmentProjectConstant;
import com.abn.assignment.configurations.DBConnectionManager;
import com.abn.assignment.dao.derbyImpl.RecipesDAODerbyImpl;

/**
 * AbpStartupContextListener.java implements ServletContextListener 
 * @author MITTAL
 * 
 *
 */
public class AbpStartupContextListener implements ServletContextListener {
	

	final static Logger logger=Logger.getLogger(RecipesDAODerbyImpl.class);
	
	/**
	 * method to initialize DB Connection 
	 *
	 */
	public void contextInitialized(ServletContextEvent sce) {
	
		try {
			logger.debug("Inside COntext");
			DBConnectionManager.getConnectionManager(AssignmentProjectConstant.CURRENT_DBTYPE);
		} catch (Exception e) {
			logger.error("Error while creating DB "+e.getMessage());
		}
	}

	/**
	 * method to close DB Connection 
	 *
	 */
	public void contextDestroyed(ServletContextEvent sce) {
	
		try {
			DBConnectionManager.getConnectionManager(AssignmentProjectConstant.CURRENT_DBTYPE).getConnection().close();
		} catch  (Exception e) {
			logger.error("Error while closing DB "+e.getMessage());
		}
		
	}

}
