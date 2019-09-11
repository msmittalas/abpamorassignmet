package nl.abp.assignment.util;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import nl.abp.assignment.model.RecipeDataBean;

public class HibernateUtil {
	final static Logger logger=Logger.getLogger(HibernateUtil.class);
	
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration().
            		addAnnotatedClass(RecipeDataBean.class)
            		.configure().
            		
            		buildSessionFactory();
            
        }
        catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }

}