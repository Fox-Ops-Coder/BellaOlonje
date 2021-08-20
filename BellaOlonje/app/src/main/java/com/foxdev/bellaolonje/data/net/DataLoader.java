package com.foxdev.bellaolonje.data.net;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.foxdev.bellaolonje.objects.CategoriesCollection;
import com.foxdev.bellaolonje.objects.Category;
import com.foxdev.bellaolonje.objects.Food;
import com.foxdev.bellaolonje.objects.FoodCollection;


import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class DataLoader {
    private static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private final APIService apiService;

    @Inject
    public DataLoader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();

        apiService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);
    }

    public void GetCategories(@NonNull Consumer<Category[]> callBack) {
        apiService.GetCategories().enqueue(new Callback<CategoriesCollection>() {
            @Override
            public void onResponse(@NonNull Call<CategoriesCollection> call,
                                   @NonNull Response<CategoriesCollection> response) {
                if (response.body() != null) {
                    callBack.accept(response.body().categories);
                } else {
                    callBack.accept(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoriesCollection> call,
                                  @NonNull Throwable t) {
                callBack.accept(null);
                t.printStackTrace();
            }
        });
    }

    public void SearchFood(@NonNull String searchData,
                           @NonNull Consumer<Food[]> callBack) {
        apiService.SearchFood(searchData).enqueue(new Callback<FoodCollection>() {
            @Override
            public void onResponse(@NonNull Call<FoodCollection> call,
                                   @NonNull Response<FoodCollection> response) {
                if (response.body() != null) {
                    callBack.accept(response.body().meals);
                } else {
                    callBack.accept(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodCollection> call,
                                  @NonNull Throwable t) {
                callBack.accept(null);
                t.printStackTrace();
            }
        });
    }

    public void GetFoodFromCategory(@NonNull String category,
                                    @NonNull Consumer<Food[]> callBack) {
        apiService.GetFoodFromCategory(category).enqueue(new Callback<FoodCollection>() {
            @Override
            public void onResponse(@NonNull Call<FoodCollection> call,
                                   @NonNull Response<FoodCollection> response) {
                if (response.body() != null) {
                    callBack.accept(response.body().meals);
                } else {
                    callBack.accept(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodCollection> call,
                                  @NonNull Throwable t) {
                callBack.accept(null);
                t.printStackTrace();
            }
        });
    }
}
