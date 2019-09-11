package nl.abp.assignment.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import nl.abp.assignment.AssignmentProjectConstant;
import nl.abp.assignment.configurations.DBConnectionManager;
import nl.abp.assignment.dao.derbyImpl.RecipesDAODerbyImpl;

public class AbpStartupContextListener implements ServletContextListener {

	final static Logger logger=Logger.getLogger(RecipesDAODerbyImpl.class);
	
	public void contextInitialized(ServletContextEvent sce) {
	
		try {
			logger.debug("Inside COntext");
			DBConnectionManager.getConnectionManager(AssignmentProjectConstant.CURRENT_DBTYPE);
		} catch (Exception e) {
			logger.error("Error while creating DB "+e.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	
		try {
			DBConnectionManager.getConnectionManager(AssignmentProjectConstant.CURRENT_DBTYPE).getConnection().close();
		} catch  (Exception e) {
			logger.error("Error while closing DB "+e.getMessage());
		}
		
	}

}
