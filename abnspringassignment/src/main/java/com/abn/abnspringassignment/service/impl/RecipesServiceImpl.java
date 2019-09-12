package com.abn.abnspringassignment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abn.abnspringassignment.dao.RecipesRepository;
import com.abn.abnspringassignment.entity.RecipesDataBean;
import com.abn.abnspringassignment.service.RecipesServices;

@Service("recipesService")
public class RecipesServiceImpl  implements RecipesServices{

	@Autowired
	private RecipesRepository recipesRepository;
	@Override
	public RecipesDataBean addOrUpdateRecipe(RecipesDataBean bean) {
		return recipesRepository.save(bean);
	}

	@Override
	public void removeRecipe(RecipesDataBean bean) {
		recipesRepository.delete(bean);
	}

	@Override
	public List<RecipesDataBean> geAllRecipes() {
		return recipesRepository.findAll();
	}
	@Override
	public RecipesDataBean getRecipeById(long id) {
		if(recipesRepository.existsById(id))
		return recipesRepository.getOne(id);
		else
		return null;
	}
}
