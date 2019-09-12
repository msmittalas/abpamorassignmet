package com.abn.abnspringassignment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abn.abnspringassignment.dao.RecipesRepository;
import com.abn.abnspringassignment.entity.RecipesDataBean;
import com.abn.abnspringassignment.service.impl.RecipesServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
public class RecipesServiceTest {

	@Mock
	private RecipesRepository recipesRepository;
	
	@InjectMocks
	private RecipesServiceImpl recipesService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllRecipes(){
		List<RecipesDataBean> recipeslist = new ArrayList<RecipesDataBean>();;
		recipeslist.add(new RecipesDataBean(1,null, "Veg", 1, "Apple,Milk", "Test", "mittal", null, null, "Apple Pie"));
		recipeslist.add(new RecipesDataBean(2,null, "Veg", 1, "Banna,Milk,Honey", "Test", "mittal", null, null, "Banna Milk Shake"));
		recipeslist.add(new RecipesDataBean(3,null, "Non-Veg", 1, "Chocolate,Milk,Egg", "Test", "mittal", null, null, "Chocolate Cake"));
		when(recipesRepository.findAll()).thenReturn(recipeslist);
		List<RecipesDataBean> result = recipesService.geAllRecipes();
		assertEquals(3, result.size());
	}
	
	
	@Test
	public void testGetRecipeById(){
		RecipesDataBean recipesDataBean = new RecipesDataBean(1,null, "Veg", 1, "Apple,Milk", "Test", "mittal", null, null, "Apple Pie");
		when(recipesRepository.getOne(1L)).thenReturn(recipesDataBean);
		when(recipesRepository.existsById(1L)).thenReturn(true);
		RecipesDataBean result = recipesService.getRecipeById(1L);
		assertEquals(1L, result.getRecipeId());
	}
	
	@Test
	public void testSaveRecipe(){
		RecipesDataBean recipesDataBean = new RecipesDataBean(60,null, "Veg", 1, "Apple,Milk", "Test", "mittal", null, null, "Apple Pie");
			when(recipesRepository.save(recipesDataBean)).thenReturn(recipesDataBean);
			RecipesDataBean result = recipesService.addOrUpdateRecipe(recipesDataBean);
		assertEquals(60, result.getRecipeId());
	}
	
	@Test
	public void removeToDo(){
		RecipesDataBean recipesDataBean = new RecipesDataBean(60,null, "Veg", 1, "Apple,Milk", "Test", "mittal", null, null, "Apple Pie");
		recipesService.removeRecipe(recipesDataBean);
        verify(recipesRepository, times(1)).delete(recipesDataBean);
	}
	
	
	
	
	
	
	
	
	
	
}
