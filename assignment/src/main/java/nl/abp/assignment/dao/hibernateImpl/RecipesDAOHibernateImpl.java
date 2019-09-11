package nl.abp.assignment.dao.hibernateImpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.classic.Session;

import nl.abp.assignment.dao.RecipesDAO;
import nl.abp.assignment.model.RecipeDataBean;
import nl.abp.assignment.util.DateConversion;
import nl.abp.assignment.util.HibernateUtil;

public class RecipesDAOHibernateImpl implements RecipesDAO {
	
	Session session;
		
	public RecipesDAOHibernateImpl() {
	
	}

	public void insertRecipes(RecipeDataBean bean, String user) throws Exception {
		
		session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
		
	}

	public void removeRecipes(RecipeDataBean bean, String user) throws Exception {
		session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		bean=getRecipe(bean);
		session.delete(bean);
		session.getTransaction().commit();
		session.close();
	
	}

	public void updateRecipes(RecipeDataBean bean, String user) throws Exception {
		RecipeDataBean outputBean=getRecipe(bean);
		outputBean.setIngredients(bean.getIngredients());
		outputBean.setInstructions(bean.getInstructions());
		outputBean.setIsVeg(bean.getIsVeg());
		outputBean.setNumberOfPeople(bean.getNumberOfPeople());
		outputBean.setRecipeName(bean.getRecipeName());
		outputBean.setUpdatedBy(user);
		outputBean.setUpdatedDate(DateConversion.toString(new Timestamp(System.currentTimeMillis())));
		session.getTransaction().commit();
		session.close();
	}

	public List<RecipeDataBean> getAll() throws Exception {
		
		session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<RecipeDataBean> beans=session.createCriteria(RecipeDataBean.class).list();
		session.close();
		return beans;
	}

	public RecipeDataBean getRecipe(RecipeDataBean bean) throws Exception {
		RecipeDataBean outputBean=null;
		session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		outputBean=(RecipeDataBean)session.load(RecipeDataBean.class,bean.getRecipeId());
		session.close();
		return outputBean;
	}

}
