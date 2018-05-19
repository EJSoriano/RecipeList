package com.RecipeList.FirstSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM recipe_ingredients WHERE recipe_id=:recipeId AND ingredient_id=:ingredientId")
    void delete(@Param("recipeId") Long recipeId, @Param("ingredientId") Long ingredientId);

}