package com.abn.abnspringassignment.exception;

public class RecipesException  extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public RecipesException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public RecipesException() {
		super();
	}
}