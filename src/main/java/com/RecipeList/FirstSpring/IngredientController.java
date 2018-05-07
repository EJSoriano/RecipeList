package com.RecipeList.FirstSpring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {
	@Autowired
	private IngredientRepository ingRepo;

	public IngredientController() {}

	// View Ingredients
	@RequestMapping("/ingredients")
	public List<Ingredient> ViewIngredients() {
		return ingRepo.findAll();
	}

	// View specific Ingredients
	@RequestMapping("/ingredients")
	public Ingredient ingredient(@RequestParam(value = "name") String name) {
		return ingRepo.findByIngredientName(name);
	}
/*
	// View ID
	@RequestMapping("/ingredients")
	public Ingredient ingredientID(@RequestParam(value="id") long id) {
		return ingRepo.findById(id);
	}
	*/
	
	// Create
	@RequestMapping(value="/Ingredient", method=RequestMethod.POST)
    public void create(@RequestBody String name) {
        ingRepo.save(new Ingredient(name));
    }
	
	// Delete by id
	@RequestMapping("/deleteIngredient")
	public void deleteIngredient(@RequestParam(value = "id") Long id) {
		ingRepo.deleteById(id);
	}
}
