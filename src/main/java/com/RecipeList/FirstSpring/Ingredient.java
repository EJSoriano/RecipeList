package com.RecipeList.FirstSpring;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@Column(name = "ingredient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "ingredient_name")
	@NotNull
    @NaturalId
	private String ingredientName;


	protected Ingredient() {
	}

	public Ingredient(String ingredient_name) {
		this.ingredientName = ingredient_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getingredientName() {
		return ingredientName;
	}

	public void setingredientName(String ingredient_name) {
		this.ingredientName = ingredient_name;
	}


	@Override
	public String toString() {
		return String.format("%s[%d]", ingredientName, id);
	}

}
