package com.abn.abnspringassignment.model;

public class RecipeResponse {

	private int status;
	private String message;

	public RecipeResponse() {
		super();
	}

	public RecipeResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}