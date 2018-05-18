package com.RecipeList.FirstSpring;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@Column(name = "recipe_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "recipe_name")
	private String recipeName;
	@ManyToMany(fetch = FetchType.LAZY, 
		cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
        })
    @JoinTable(name = "recipe_ingredients", 
    	joinColumns = @JoinColumn(name = "ingredient_id"), 
    	inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	private List<Ingredient> ingredients = new ArrayList<>();

	protected Recipe() {
	}

	public Recipe(String name) {
		this.recipeName = name;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String name) {
		this.recipeName = name;
	}


	public long getId() {
		return id;
	}

	public void setId(long recipe_id) {
		this.id = recipe_id;
	}

	public void removeIngredients(Ingredient ingredient) {
		if (ingredients.contains(ingredient)) {
			ingredients.remove(ingredient);
		}
	}

	public void addIngredients(Ingredient ingredient) {
		if (!ingredients.contains(ingredient)) {
			ingredients.add(ingredient);
		}
	}

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		String result = String.format("Name: %s[%d]\nIngredients:\n", recipeName, id);
		for(Ingredient ing : ingredients) {
			result += String.format("%s\n", ing.toString());
		}
		return result;
	}
}
