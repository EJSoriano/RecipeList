package com.RecipeList.FirstSpring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

	@Autowired
	private RecipeRepository repRepo;
	
	public RecipeController() {}
	
	//View
	@RequestMapping("/ViewRecipes")
	public List<Recipe> ViewRecipes(){
		return repRepo.findAll();
	}
	
	//View specific
	@RequestMapping("/Recipe")
	public Recipe recipe(@RequestParam(value="name") String name) {
		return repRepo.findByRecipeName(name);
	}
	
	//Delete by id
	@RequestMapping("/deleteRecipe")
	public void deleteRecipe(@RequestParam(value="id") Long id) {
		repRepo.deleteById(id);
	}
	
	
}
