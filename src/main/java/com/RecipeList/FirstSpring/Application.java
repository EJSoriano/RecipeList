package com.RecipeList.FirstSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("module-service")
public class Application implements CommandLineRunner {

	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	
    	ingredientRepo.deleteAllInBatch();
		recipeRepo.deleteAllInBatch();
		
		Ingredient ing1 = new Ingredient("Fettucine");
		Ingredient ing2 = new Ingredient("Alfredo Sauce");
		
		
		Recipe recipe1 = new Recipe("Chicken Alfredo");
		
		recipe1.getIngredients().add(ing1);
		recipe1.getIngredients().add(ing2);
		
		ing1.getRecipes().add(recipe1);
		ing2.getRecipes().add(recipe1);
		
		recipeRepo.save(recipe1);
		
    }
}