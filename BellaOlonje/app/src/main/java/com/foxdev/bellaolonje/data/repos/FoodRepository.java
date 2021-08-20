package com.foxdev.bellaolonje.data.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.foxdev.bellaolonje.data.net.DataLoader;
import com.foxdev.bellaolonje.objects.Category;
import com.foxdev.bellaolonje.objects.Food;

import javax.inject.Inject;

public final class FoodRepository {
    private final DataLoader dataLoader;
    private final MutableLiveData<Category[]> categories;
    private final MutableLiveData<Food[]> meals;

    @Inject
    public FoodRepository(DataLoader dataLoader) {
        this.dataLoader = dataLoader;

        categories = new MutableLiveData<>();
        meals = new MutableLiveData<>();
    }

    public void GetCategories() {
        dataLoader.GetCategories(categories::postValue);
    }

    public void SearchFood(@NonNull String searchData) {
        dataLoader.SearchFood(searchData, meals::postValue);
    }

    public void GetFoodFromCategory(@NonNull String category) {
        dataLoader.GetFoodFromCategory(category, meals::postValue);
    }

    @NonNull
    public LiveData<Category[]> GetCategoriesLiveData() { return categories; }

    @NonNull
    public LiveData<Food[]> GetFoodLiveData() { return meals; }

    public void ClearMealLiveData() {
        meals.setValue(new Food[0]);
    }
}