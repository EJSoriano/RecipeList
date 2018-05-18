package com.RecipeList.FirstSpring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

	@Autowired
	private RecipeRepository recRepo;

	@Autowired
	private IngredientRepository ingRepo;

	public RecipeController() {
	}

	// View/Search Recipes
	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public List<Recipe> recipes(@RequestParam(value = "name", required = false) String name) {
		if (name == null)
			return recRepo.findAll();
		else
			return recRepo.findByRecipeName(name);
	}

	// View ID
	@RequestMapping("/recipes/{id}")
	public Optional<Recipe> recipeByID(@PathVariable long id) {
		return recRepo.findById(id);
	}

	// Create recipe
	@RequestMapping(value = "/recipes", method = RequestMethod.POST)
	public void create(@RequestBody Recipe recipe) {
		recRepo.save(recipe);
	}

	// View ingredients
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.GET)
	public List<Ingredient> recipeIngredients(@PathVariable long id) {
		Optional<Recipe> check = recRepo.findById(id);
		if (check.isPresent()) {
			Recipe recipe = check.get();
			return recipe.getIngredients();
		}
		return null;
	}

	// Add ingredient
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.PUT)
	public void addIngredient(@PathVariable long id, @RequestBody Ingredient newIng) {
		Optional<Recipe> checkRec = recRepo.findById(id);
		Ingredient oldIng = findIngredient(newIng.getingredientName());
		// Recipe existential check
		if (checkRec.isPresent()) {
			Recipe recipe = checkRec.get();
			// if ingredient doesn't exist, create it and add it
			if (oldIng == null) {
				Ingredient ingredient = new Ingredient(newIng.getingredientName());
				recipe.addIngredients(ingredient);
			// if ingredient exists, add it if it's not already there
			} else 
				recipe.addIngredients(oldIng);
			recRepo.save(recipe);
		}

	}
	
	// Delete ingredient from recipe
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
		Optional<Recipe> checkRec = recRepo.findById(id);
		Ingredient oldIng = findIngredient(ingredient.getingredientName());
		// Recipe existential check
		if (checkRec.isPresent()) {
			Recipe recipe = checkRec.get();
			// if ingredient is in recipe, remove it
			if (oldIng != null) 
				recipe.removeIngredients(ingredient);
			recRepo.save(recipe);
		}
		
	}
	
	// If ingredient exists (by name), return it. Else null.
	private Ingredient findIngredient(String name) {
		List<Ingredient> result = ingRepo.findByIngredientName(name);
		if (result.size() > 0) {
			return result.get(0);
		} else
			return null;
	}
	/*
	 * @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.PUT)
	 * public void update(@PathVariable long id, @RequestBody Ingredient updatedIng)
	 * { Optional<Ingredient> ing = ingredientByID(id); if(ing.isPresent()) {
	 * Ingredient currentIngredient = ing.get();
	 * updatedIng.setId(currentIngredient.getId()); ingRepo.save(updatedIng); } }
	 */

	// Delete by id
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
	public void deleteRecipe(@PathVariable long id) {
		recRepo.deleteById(id);
	}

}
