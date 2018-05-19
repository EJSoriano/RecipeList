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

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RestController
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private RecipeIngredientRepository recipeIngredientRepository;

	public RecipeController() {
	}

	// View/Search Recipes
	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public List<Recipe> recipes(@RequestParam(value = "name", required = false) String name) {
		if (name == null)
			return recipeRepository.findAll();
		else
			return recipeRepository.findByName(name);
	}

	// View ID
	@RequestMapping("/recipes/{id}")
	public Optional<Recipe> recipeByID(@PathVariable long id) {
		return recipeRepository.findById(id);
	}

	// Create recipe
	@RequestMapping(value = "/recipes", method = RequestMethod.POST)
	public void create(@RequestBody Recipe recipe) {
		recipeRepository.save(recipe);
	}

	// View ingredients
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.GET)
	public List<RecipeIngredient> recipeIngredients(@PathVariable long id) {
		Optional<Recipe> check = recipeRepository.findById(id);
		if (check.isPresent()) {
			Recipe recipe = check.get();
			return recipe.getIngredients();
		}
		return null;
	}

	// Add ingredient
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.PUT)
	public void addIngredient(@PathVariable long id, long ingredientId, String quantity) {
		Optional<Recipe> checkRec = recipeRepository.findById(id);
		Optional<Ingredient> checkIng = ingredientRepository.findById(ingredientId);
		if (checkRec.isPresent()) {
			Recipe recipe = checkRec.get();
			if (checkIng.isPresent()) {

				RecipeIngredient ri = new RecipeIngredient();
				ri.setQuantity(quantity);
				ri.setIngredient(checkIng.get());
				ri.setRecipe(checkRec.get());

				Ingredient ingredient = checkIng.get();
				ingredient.addRecipe(ri);
				recipe.addIngredient(ri);
				recipeRepository.save(recipe);
			}
		}

	}

//	// Delete ingredient from recipe
	@Transactional
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable long id, @RequestBody List<Long> ingList) {

		ingList.stream().forEach(ingredientId -> recipeIngredientRepository.delete(id, ingredientId));

//		Optional<Recipe> checkRec = recipeRepository.findById(id);
//		if (checkRec.isPresent()) {
//			Recipe recipe = checkRec.get();
//			for (Long ingID : ingList) {
//				Optional<Ingredient> checkIng = ingredientRepository.findById(ingID);
//				if (checkIng.isPresent()) {
//					Ingredient ingredient = checkIng.get();
//					RecipeIngredient recipeIngredient = new RecipeIngredient();
//					recipeIngredient.setRecipe(recipe);
//					recipeIngredient.setIngredient(ingredient);
//					recipeIngredientRepository.delete(recipeIngredient);
//				}
//
//			}
//		}

	}

	// Update recipe name
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
	public void updateName(@PathVariable long id, @RequestBody Recipe newRec) {
		Optional<Recipe> checkRec = recipeRepository.findById(id);
		if (checkRec.isPresent()) {
			Recipe oldRec = checkRec.get();
			oldRec.setName(newRec.getName());
			recipeRepository.save(oldRec);
		}
	}

	// Update recipe ingredients

	// Delete by id
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
	public void deleteRecipe(@PathVariable long id) {
		recipeRepository.deleteById(id);
	}

}
