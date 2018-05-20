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

	private RecipeRepository recipeRepository;

	private IngredientRepository ingredientRepository;

	private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
                            RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
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
	public void addIngredient(@PathVariable long id, @RequestBody List<RecipeIngredientWrapper> ingList) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if (recipe.isPresent()) {
			for (RecipeIngredientWrapper ing : ingList) {
				Optional<Ingredient> checkIng = ingredientRepository.findById(ing.getIngredientID());
				if (checkIng.isPresent()) {
					Ingredient ingredient = checkIng.get();
					RecipeIngredient recipeIngredient = new RecipeIngredient();
					recipeIngredient.setQuantity(ing.getQuantity());
					recipeIngredient.setIngredient(ingredient);
					recipeIngredient.setRecipe(recipe.get());
	
					ingredient.addRecipe(recipeIngredient);
					recipe.get().addIngredient(recipeIngredient);
					recipeRepository.save(recipe.get());
				}
			}
		}
	}

	// // Delete ingredient from recipe
	@Transactional
	@RequestMapping(value = "/recipes/{id}/ingredients", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathVariable long id, @RequestBody List<Long> ingredientIds) {
		recipeIngredientRepository.delete(id, ingredientIds);
	}

	// Update recipe name
    @Transactional
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
