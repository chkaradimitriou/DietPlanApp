package com.ckaradimitriou.dietplanapp.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public final String BASE_URL = "https://api.edamam.com/";

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final RecipesApiEndpoints service = retrofit.create(RecipesApiEndpoints.class);

    public RecipesApiEndpoints getService() {
        return service;
    }
}
