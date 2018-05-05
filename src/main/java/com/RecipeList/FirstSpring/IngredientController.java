package com.RecipeList.FirstSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {
	@Autowired
	private IngredientRepository ingRepo;

	public IngredientController() {}

	// View
	@RequestMapping("/ViewIngredients")
	public List<Ingredient> ViewIngredients() {
		return ingRepo.findAll();
	}

	// View specific
	@RequestMapping("/Ingredient")
	public Ingredient ingredient(@RequestParam(value = "name") String name) {
		return ingRepo.findByIngredientName(name);
	}

	// Delete by id
	@RequestMapping("/deleteIngredient")
	public void deleteIngredient(@RequestParam(value = "id") Long id) {
		ingRepo.deleteById(id);
	}
}
