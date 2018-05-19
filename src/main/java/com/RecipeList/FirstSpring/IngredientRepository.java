package com.RecipeList.FirstSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
	
	List<Ingredient> findByName(String name);
	
}
