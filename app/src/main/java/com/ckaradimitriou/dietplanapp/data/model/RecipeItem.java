package com.ckaradimitriou.dietplanapp.data.model;

public class RecipeItem {

    private Recipe recipe;

    public RecipeItem(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
