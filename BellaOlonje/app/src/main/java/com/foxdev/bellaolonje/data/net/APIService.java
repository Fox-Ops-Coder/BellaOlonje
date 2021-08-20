package com.foxdev.bellaolonje.data.net;

import androidx.annotation.NonNull;

import com.foxdev.bellaolonje.objects.CategoriesCollection;
import com.foxdev.bellaolonje.objects.FoodCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("categories.php")
    @NonNull
    Call<CategoriesCollection> GetCategories();

    @GET("search.php?")
    @NonNull
    Call<FoodCollection> SearchFood(@NonNull @Query("s") String searchData);

    @GET("filter.php?")
    @NonNull
    Call<FoodCollection> GetFoodFromCategory(@NonNull @Query("c") String category);
}
