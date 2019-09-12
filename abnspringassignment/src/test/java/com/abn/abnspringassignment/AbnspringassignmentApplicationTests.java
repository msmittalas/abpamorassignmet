package com.abn.abnspringassignment;

import org.junit.Test;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbnspringassignmentApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbnspringassignmentApplicationTests{

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifygetAllRecipes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(3))).andDo(print());
	}
	
	@Test
	public void verifygetRecipeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.recipeId").exists())
		.andExpect(jsonPath("$.isVeg").exists())
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidRecipeArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes/B").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	
	@Test
	public void verifyInvalidRecipeId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Recipe doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyNullRecipes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Recipe doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteRecipes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/recipes/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Recipe has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidRecipeDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/recipes/10").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Recipe doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/recipes/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("  {\n" + 
        		"        \"isVeg\": \"Veg\",\n" + 
        		"        \"numberOfPeople\": 1,\n" + 
        		"        \"ingredients\": \"Banna,Mango,Milk,Honey\",\n" + 
        		"        \"instructions\": \"Test\",\n" + 
        		"        \"createdBy\": \"mittal\",\n" + 
        		"        \"recipeName\": \"Banna Mango Milk Shake\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.recipeId").exists())
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/recipes/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"    \"recipeId\": 14,\n" + 
        		"    \"createdDate\": null,\n" + 
        		"    \"isVeg\": \"Veg\",\n" + 
        		"    \"numberOfPeople\": 1,\n" + 
        		"    \"ingredients\": \"Banna,Mango,Milk,Honey\",\n" + 
        		"    \"instructions\": \"Test\",\n" + 
        		"    \"createdBy\": \"mittal\",\n" + 
        		"    \"updatedBy\": null,\n" + 
        		"    \"updatedDate\": null,\n" + 
        		"    \"recipeName\": \"Banna Mango Milk Shake\"\n" + 
        		"}")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
		.andDo(print());
	}
	
	@Test
	public void verifyUpdateRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/recipes/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("  {\n" + 
        		"  	 \"recipeId\": 2,\n" + 
        		"        \"isVeg\": \"Veg\",\n" + 
        		"        \"numberOfPeople\": 1,\n" + 
        		"        \"ingredients\": \"Banna,Mango,Milk,Honey\",\n" + 
        		"        \"instructions\": \"Test\",\n" + 
        		"        \"createdBy\": \"mittal\",\n" + 
        		"        \"recipeName\": \"Banna Mango Milk Shake\"\n" + 
        		"    }")
        .accept(MediaType.APPLICATION_JSON))
	.andExpect(jsonPath("$.isVeg").exists())
		.andExpect(jsonPath("$.numberOfPeople").exists())
		.andExpect(jsonPath("$.recipeId").value(2))
		.andExpect(jsonPath("$.isVeg").value("Veg"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidRecipeUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/recipes/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("  {\n" + 
        		"  	 \"recipeId\": 10,\n" + 
        		"        \"isVeg\": \"Veg\",\n" + 
        		"        \"numberOfPeople\": 1,\n" + 
        		"        \"ingredients\": \"Banna,Mango,Milk,Honey\",\n" + 
        		"        \"instructions\": \"Test\",\n" + 
        		"        \"createdBy\": \"mittal\",\n" + 
        		"        \"recipeName\": \"Banna Mango Milk Shake\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Recipe doesn´t exist"))
		.andDo(print());
	}

}