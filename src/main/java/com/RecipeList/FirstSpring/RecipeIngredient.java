package com.RecipeList.FirstSpring;

import javax.persistence.*;
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	
	

	public RecipeIngredient() {
	}

}
