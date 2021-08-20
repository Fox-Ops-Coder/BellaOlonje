package com.foxdev.bellaolonje.activities;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.core.AppActivity;
import com.foxdev.bellaolonje.fragments.OrdersFragment;
import com.foxdev.bellaolonje.fragments.SearchFragment;
import com.foxdev.bellaolonje.fragments.StoreFragment;
import com.foxdev.bellaolonje.objects.Category;
import com.foxdev.bellaolonje.objects.Food;
import com.foxdev.bellaolonje.viewmodel.AppViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public final class StoreActivity extends AppActivity {

    private Consumer<Void> returnToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Intent intent = getIntent();
        Parcelable[] objects = intent.getParcelableArrayExtra(categoriesName);

        final int length = objects.length;
        Category[] categories = new Category[length];

        for (int index = 0; index < length; ++index) {
            categories[index] = (Category) objects[index];
        }

        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.storeFragmentsContainer, StoreFragment.NewInstance(categories))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        getWindow().getDecorView().setSystemUiVisibility(hideSystemUIValue);

        BottomNavigationView bottomNavigationView =
                (BottomNavigationView)findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnItemSelectedListener(item -> {
                    if (item.getItemId() == R.id.homeMenuItem) {
                        fragmentManager.beginTransaction()
                                .replace(R.id.storeFragmentsContainer,
                                        StoreFragment.NewInstance(categories))
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    } else {
                        fragmentManager.beginTransaction()
                                .replace(R.id.storeFragmentsContainer,
                                        new OrdersFragment())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }

                    return true;
                });

        final int homeId = bottomNavigationView.getSelectedItemId();

        returnToStore = v -> {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.storeFragmentsContainer, StoreFragment.NewInstance(categories))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            bottomNavigationView.setSelectedItemId(homeId);
        };
    }

    public void GoToOrders() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.storeFragmentsContainer, new OrdersFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void ReturnToStore() {
        returnToStore.accept(null);
        View navBar = findViewById(R.id.bottomNavigationView);

        if (navBar.getVisibility() == View.GONE) navBar.setVisibility(View.VISIBLE);
    }

    public void LetsSearch() {
        appViewModel.ClearMealLiveData();
        findViewById(R.id.bottomNavigationView).setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.storeFragmentsContainer, new SearchFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void GetMealsFromCategory(@NonNull String category) {
        appViewModel.GetFoodFromCategory(category);
    }

    public void SearchMeals(@NonNull String searchData) {
        appViewModel.SearchFood(searchData);
    }

    public LiveData<Food[]> GetMealLiveData() {
        return appViewModel.GetMealsLiveData();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.storeFragmentsContainer);

        if (currentFragment instanceof StoreFragment) {
            super.onBackPressed();
        } else {
            ReturnToStore();
        }
    }
}