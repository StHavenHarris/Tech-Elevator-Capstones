package com.techelevator.dao;
import com.techelevator.model.Recipes;

import java.util.List;
public interface RecipesDAO {
    public void addNewRecipe(Recipes recipe, int userId);

    public void addIngredientsToRecipe(int recipeId, int ingredientId, int unitId, int quantityId);

    public List<Recipes> getAllRecipes();

    public void updateRecipe(Recipes recipe, String recipeName, String instructions);

    public void deleteRecipe(int recipeId);

}
