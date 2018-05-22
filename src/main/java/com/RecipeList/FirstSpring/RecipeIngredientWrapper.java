package com.RecipeList.FirstSpring;

public class RecipeIngredientWrapper {

	private long ingredientID;
	private String quantity;
	
	public RecipeIngredientWrapper() {}
	
	public long getIngredientID() {
		return ingredientID;
	}

	public void setIngredientID(long ingredientID) {
		this.ingredientID = ingredientID;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public RecipeIngredientWrapper withIngredientId(long ingredientID) {
		this.ingredientID = ingredientID;
		return this;
	}

	public RecipeIngredientWrapper withQuantity(String quantity) {
		this.quantity = quantity;
		return this;
	}

}
