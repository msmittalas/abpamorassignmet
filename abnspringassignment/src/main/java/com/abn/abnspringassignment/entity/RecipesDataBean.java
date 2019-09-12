package com.abn.abnspringassignment.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecipesDataBean {
	

	@Id
	@GeneratedValue
	private long recipeId;
	private Timestamp createdDate;
	private String isVeg;
	private int numberOfPeople;
	private String ingredients;
	private String instructions;
	private String createdBy;
	private String updatedBy;
	private Timestamp updatedDate;
	private String recipeName;
	
	
	public RecipesDataBean() {}
	public RecipesDataBean(long recipeId, Timestamp createdDate, String isVeg, int numberOfPeople, String ingredients,
			String instructions, String createdBy, String updatedBy, Timestamp updatedDate, String recipeName) {
		super();
		this.recipeId = recipeId;
		this.createdDate = createdDate;
		this.isVeg = isVeg;
		this.numberOfPeople = numberOfPeople;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.recipeName = recipeName;
	}
	
	
	public long getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(String isVeg) {
		this.isVeg = isVeg;
	}
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
	
	
	
	
}