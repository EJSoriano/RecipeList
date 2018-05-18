package com.RecipeList.FirstSpring;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@Column(name = "ingredient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "ingredient_name", unique = true, nullable = false)
	@NotNull
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
			mappedBy = "ingredient")
	private List<RecipeIngredient> recipes = new ArrayList<>();

	protected Ingredient() {
	}

	public Ingredient(String ingredient_name) {
		this.name = ingredient_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String ingredient_name) {
		this.name = ingredient_name;
	}

	public void addRecipe(RecipeIngredient recIng) {
		if (!recipes.contains(recIng))
			recipes.add(recIng);
	}
	
	@JsonIgnore
	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}

	@Override
	public String toString() {
		return String.format("%s[%d]", name, id);
	}

}
