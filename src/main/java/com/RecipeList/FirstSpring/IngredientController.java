package com.RecipeList.FirstSpring;

import java.util.List;
import java.util.Optional;

import com.RecipeList.FirstSpring.exception.ItemDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngredientController {
	
	@Autowired
	private IngredientRepository ingredientRepository;

	public IngredientController() {
	}

	// View/Search Ingredients
	@RequestMapping(value = "/ingredients", method = RequestMethod.GET)
	public List<Ingredient> ingredients(@RequestParam(value = "name", required = false) String name) {
		if (name == null)
			return ingredientRepository.findAll();
		else
			return ingredientRepository.findByName(name);
	}

	// View ID
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET)
	public Optional<Ingredient> ingredientByID(@PathVariable long id) {
		return ingredientRepository.findById(id);
	}

	// Create ingredient
	@RequestMapping(value = "/ingredients", method = RequestMethod.POST)
	public Ingredient create(@RequestBody Ingredient ingredient) {

		return ingredientRepository.save(ingredient);

	}

	// Update
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.PUT)
	public Ingredient update(@PathVariable long id, @RequestBody Ingredient updatedIng) {
		Optional<Ingredient> ing = ingredientByID(id);
		if(ing.isPresent()) {
			Ingredient currentIngredient = ing.get();
			updatedIng.setId(currentIngredient.getId());
			return ingredientRepository.save(updatedIng);
		}
		throw new ItemDoesNotExistException("Ingredient", id);

	}

	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.DELETE)
	public void deleteIngredientById(@PathVariable long id, @RequestBody List<Long> ingList) {
		for (long ingID : ingList)
			ingredientRepository.deleteById(ingID);
	}
}
