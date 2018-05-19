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
		Recipe recipe1 = new Recipe("Chicken Alfredo");
		RecipeIngredient ri1 = new RecipeIngredient();
		
		ri1.setQuantity("3");
		ri1.setRecipe(recipe1);
		ri1.setIngredient(ing1);
		
		ing1.addRecipe(ri1);
		recipe1.addIngredient(ri1);

		recipeRepo.save(recipe1);
		ingredientRepo.save(ing1);
		recIngRepo.save(ri1);


	}
}
*/
