package com.ckaradimitriou.dietplanapp.data.network;


import com.ckaradimitriou.dietplanapp.data.model.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipesApiEndpoints {

    @GET("api/recipes/v2")
    Call<RecipeResponse> searchForRecipesByMealType(
            @Query("type") String type,
            @Query("app_id") String appId,
            @Query("app_key") String appKey,
            @Query("mealType") String mealType
    );
}
