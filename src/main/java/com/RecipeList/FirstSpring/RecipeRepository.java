package com.RecipeList.FirstSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	
	List<Recipe> findByName(String name);
	
	
}
