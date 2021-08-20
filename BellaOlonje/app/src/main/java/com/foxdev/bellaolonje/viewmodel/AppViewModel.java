package com.foxdev.bellaolonje.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.foxdev.bellaolonje.data.repos.FoodRepository;
import com.foxdev.bellaolonje.objects.Category;
import com.foxdev.bellaolonje.objects.Food;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public final class AppViewModel extends ViewModel {
    private final FoodRepository foodRepository;

    @Inject
    public AppViewModel(@NonNull FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void LoadCategories() {
        foodRepository.GetCategories();
    }

    public void SearchFood(@NonNull String searchData) {
        foodRepository.SearchFood(searchData);
    }

    public void GetFoodFromCategory(@NonNull String category) {
        foodRepository.GetFoodFromCategory(category);
    }

    public LiveData<Category[]> GetCategoriesLiveData() {
        return foodRepository.GetCategoriesLiveData();
    }

    public LiveData<Food[]> GetMealsLiveData() {
        return foodRepository.GetFoodLiveData();
    }

    public void ClearMealLiveData() {
        foodRepository.ClearMealLiveData();
    }
}
