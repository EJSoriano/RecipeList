package com.RecipeList.FirstSpring;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@Column(name = "recipe_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "recipe_name", unique = true, nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY,
		cascade = {CascadeType.PERSIST, CascadeType.MERGE},
		mappedBy = "recipe")
	private List<RecipeIngredient> ingredients = new ArrayList<>();

	protected Recipe() {
	}

	public Recipe(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long recipe_id) {
		this.id = recipe_id;
	}

	public void removeIngredient(RecipeIngredient ingredient) {
		if (ingredients.contains(ingredient)) {
			ingredients.remove(ingredient);
		}
	}

	public void addIngredient(RecipeIngredient ingredient) {
		if (!ingredients.contains(ingredient)) {
			ingredients.add(ingredient);
		}
	}

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }
    
	
	@Override
	public String toString() {
		String result = String.format("Name: %s[%d]\nIngredients:\n", name, id);
		for(RecipeIngredient ing : ingredients) {
			result += String.format("%s\n", ing.toString());
		}
		return result;
	}

}
