package com.RecipeList.FirstSpring;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof RecipeIngredient)) {
			return false;
		}
		RecipeIngredient recipeIngredient = (RecipeIngredient) o;
		return recipe.equals(recipeIngredient.getRecipe()) && ingredient.equals(recipeIngredient.getIngredient());

	}

	//NEVER OVERRIDE EQUALS WITHOUT OVERRIDING HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(recipe, ingredient);
	}

}
