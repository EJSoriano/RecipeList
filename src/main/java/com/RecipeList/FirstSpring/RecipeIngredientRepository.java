package com.RecipeList.FirstSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM recipe_ingredients WHERE recipe_id=:recipeId AND ingredient_id IN (:ingredientIds)")
    void delete(@Param("recipeId") Long recipeId, @Param("ingredientIds") List<Long> ingredientIds);

}