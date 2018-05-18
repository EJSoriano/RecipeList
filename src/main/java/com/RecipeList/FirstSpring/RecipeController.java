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

	@Autowired
	private RecipeIngredientRepository riRepo;

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
	public List<RecipeIngredient> recipeIngredients(@PathVariable long id) {
		Optional<Recipe> check = recRepo.findById(id);
		if (check.isPresent()) {
			Recipe recipe = check.get();
			return recipe.getIngredients();
		}
		return null;
	}

	// Add ingredient
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.PUT)
	public void addIngredient(@PathVariable long id, @RequestBody RecipeIngredient ingredient) {
		Optional<Recipe> checkRec = recRepo.findById(id);
		Optional<Ingredient> checkIng = ingRepo.findById(ingredient.getIngredient().getId());
		if (checkRec.isPresent()) {
			Recipe recipe = checkRec.get();
			if (checkIng.isPresent()) {
				recipe.addIngredients(ingredient);
				recRepo.save(recipe);
			}
		}

	}

	// Delete ingredient from recipe
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable long id, @RequestBody RecipeIngredient ingredient) {
		Optional<Recipe> checkRec = recRepo.findById(id);
		Optional<Ingredient> checkIng = ingRepo.findById(ingredient.getIngredient().getId());
		if (checkRec.isPresent()) {
			Recipe recipe = checkRec.get();
			if (checkIng.isPresent()) {
				recipe.removeIngredients(ingredient);
				recRepo.save(recipe);
			}
		}

	}

	// Update recipe name
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
	public void updateName(@PathVariable long id, @RequestBody Recipe newRec) {
		Optional<Recipe> checkRec = recRepo.findById(id);
		if (checkRec.isPresent()) {
			Recipe oldRec = checkRec.get();
			oldRec.setName(newRec.getName());
			recRepo.save(oldRec);
		}
	}
	
	// Update recipe ingredients

	// Delete by id
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.DELETE)
	public void deleteRecipe(@PathVariable long id) {
		recRepo.deleteById(id);
	}

}
