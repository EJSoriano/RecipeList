package com.RecipeList.FirstSpring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngredientController {
	
	@Autowired
	private IngredientRepository ingRepo;

	public IngredientController() {
	}

	// View/Search Ingredients
	@RequestMapping(value = "/ingredients", method = RequestMethod.GET)
	public List<Ingredient> ingredients(@RequestParam(value = "name", required = false) String name) {
		if (name == null)
			return ingRepo.findAll();
		else
			return ingRepo.findByIngredientName(name);
	}

	// View ID
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET)
	public Optional<Ingredient> ingredientByID(@PathVariable long id) {
		return ingRepo.findById(id);
	}

	// Create ingredient
	@RequestMapping(value = "/ingredients", method = RequestMethod.POST)
	public void create(@RequestBody Ingredient ingredient) {
		ingRepo.save(ingredient);
	}

	// Update
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Ingredient updatedIng) {
		Optional<Ingredient> ing = ingredientByID(id);
		if(ing.isPresent()) {
			Ingredient currentIngredient = ing.get();
			updatedIng.setId(currentIngredient.getId());
			ingRepo.save(updatedIng);
		}
	}

	// Delete by id
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable long id) {
		ingRepo.deleteById(id);
	}
}
