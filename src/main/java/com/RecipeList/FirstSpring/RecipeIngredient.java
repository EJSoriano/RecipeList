package com.RecipeList.FirstSpring;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	
	@Column(name = "quantity")
	private String quantity;

	public RecipeIngredient() {
	}

	@JsonIgnore
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@JsonIgnore
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public long getId() {
		return ingredient.getId();
	}
	
	public String getName() {
		return ingredient.getName();
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
