package com.ckaradimitriou.dietplanapp.data.model;

import java.util.List;

public class Recipe {

    private String label;
    private String image;
    private String url;
    private List<String> ingredientLines;
    private Double calories;
    private Double totalWeight;
    private List<String> cuisineType;
    private NutrientResponse totalNutrients;

    public Recipe(String label, String image, String url, List<String> ingredientLines, Double calories, Double totalWeight, List<String> cuisineType, NutrientResponse totalNutrients) {
        this.label = label;
        this.image = image;
        this.url = url;
        this.ingredientLines = ingredientLines;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.cuisineType = cuisineType;
        this.totalNutrients = totalNutrients;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(List<String> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public NutrientResponse getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(NutrientResponse totalNutrients) {
        this.totalNutrients = totalNutrients;
    }
}
