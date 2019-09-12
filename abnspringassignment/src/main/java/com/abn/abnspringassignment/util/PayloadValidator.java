package com.abn.abnspringassignment.util;

import com.abn.abnspringassignment.entity.RecipesDataBean;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(RecipesDataBean bean){
		if (bean.getRecipeId() > 0){
			return false;
		}
		return true;
	}

}