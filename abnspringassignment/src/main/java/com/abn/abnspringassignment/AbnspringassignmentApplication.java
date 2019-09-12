package com.abn.abnspringassignment;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abn.abnspringassignment.entity.RecipesDataBean;
import com.abn.abnspringassignment.service.RecipesServices;

@SpringBootApplication
public class AbnspringassignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbnspringassignmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(RecipesServices services)
	{
		return (arguments) ->
		{
		services.addOrUpdateRecipe(new RecipesDataBean(1,null, "Veg", 1, "Apple,Milk", "Test", "mittal", null, null, "Apple Pie"));
		services.addOrUpdateRecipe(new RecipesDataBean(2,null, "Veg", 1, "Banna,Milk,Honey", "Test", "mittal", null, null, "Banna Milk Shake"));
		services.addOrUpdateRecipe(new RecipesDataBean(3,null, "Non-Veg", 1, "Chocolate,Milk,Egg", "Test", "mittal", null, null, "Chocolate Cake"));
		};
	}
}
