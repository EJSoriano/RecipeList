package com.RecipeList.FirstSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
}
/*

@SpringBootApplication
@ComponentScan("module-service")
public class Application implements CommandLineRunner {

	@Autowired
	private RecipeRepository recipeRepo;

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private RecipeIngredientRepository recIngRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ingredientRepo.deleteAllInBatch();
		recipeRepo.deleteAllInBatch();
		recIngRepo.deleteAllInBatch();

		Ingredient ing1 = new Ingredient("Chicken Breast");
		Ingredient ing2 = new Ingredient("Fettucine");
		Ingredient ing3 = new Ingredient("Alfredo Sauce");

		Recipe recipe1 = new Recipe("Chicken Alfredo");

		RecipeIngredient ri1 = new RecipeIngredient();
		ri1.setQuantity("3");
		RecipeIngredient ri2 = new RecipeIngredient();
		ri2.setQuantity("1 handful");
		RecipeIngredient ri3 = new RecipeIngredient();
		ri3.setQuantity("1 cup");
		
		recipe1.addIngredients(ri1);
		recipe1.addIngredients(ri2);
		recipe1.addIngredients(ri3);

		ri1.setRecipe(recipe1);
		ri2.setRecipe(recipe1);
		ri3.setRecipe(recipe1);
		
		ing1.addRecipe(ri1);
		ing2.addRecipe(ri2);
		ing3.addRecipe(ri3);
		
		ri1.setIngredient(ing1);
		ri2.setIngredient(ing2);
		ri3.setIngredient(ing3);

		recipeRepo.save(recipe1);
		ingredientRepo.save(ing1);
		ingredientRepo.save(ing2);
		ingredientRepo.save(ing3);
		recIngRepo.save(ri1);
		recIngRepo.save(ri2);
		recIngRepo.save(ri3);

	}
}
*/