package com.ckaradimitriou.dietplanapp.data.model;

import com.google.gson.annotations.SerializedName;

public class NutrientResponse {

    @SerializedName("ENERC_KCAL")
    private Nutrient kcal;

    @SerializedName("FAT")
    private Nutrient fat;

    @SerializedName("PROCNT")
    private Nutrient protein;

    @SerializedName("CHOCDF")
    private Nutrient carbs;
}
