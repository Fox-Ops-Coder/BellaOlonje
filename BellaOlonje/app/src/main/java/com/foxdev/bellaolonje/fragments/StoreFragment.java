package com.foxdev.bellaolonje.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.activities.StoreActivity;
import com.foxdev.bellaolonje.adapters.MealsAdapter;
import com.foxdev.bellaolonje.core.AppActivity;
import com.foxdev.bellaolonje.objects.Category;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public final class StoreFragment extends Fragment {

    public StoreFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static StoreFragment NewInstance(@NonNull Category[] categories) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArray(AppActivity.categoriesName, categories);

        StoreFragment storeFragment = new StoreFragment();
        storeFragment.setArguments(bundle);

        return  storeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        Bundle bundle = getArguments();
        assert bundle != null;

        TabLayout tabLayout = view.findViewById(R.id.storeTabs);
        Parcelable[] objects = bundle.getParcelableArray(AppActivity.categoriesName);

        for (Parcelable object : objects) {
            Category category = (Category) object;
            tabLayout.addTab(tabLayout.newTab().setText(category.strCategory));
        }

        MealsAdapter mealsAdapter = new MealsAdapter();
        RecyclerView mealsList = view.findViewById(R.id.mealsList);
        mealsList.setAdapter(mealsAdapter);

        StoreActivity storeActivity = (StoreActivity) requireActivity();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                storeActivity.GetMealsFromCategory((String) Objects
                        .requireNonNull(tab.getText()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                storeActivity.GetMealsFromCategory((String) Objects
                        .requireNonNull(tab.getText()));
            }
        });

        storeActivity.GetMealLiveData().observe(getViewLifecycleOwner(), mealsAdapter::SetFood);

        storeActivity.GetMealsFromCategory((String)
                Objects
                        .requireNonNull(Objects
                                .requireNonNull(tabLayout
                                        .getTabAt(tabLayout
                                                .getSelectedTabPosition()))
                                .getText()));

        view.findViewById(R.id.cardButton).setOnClickListener(v -> {
            storeActivity.GetMealLiveData().removeObservers(getViewLifecycleOwner());
            ((StoreActivity)requireActivity()).GoToOrders();
        });

        view.findViewById(R.id.searchBox).setOnClickListener(v -> {
            storeActivity.GetMealLiveData().removeObservers(getViewLifecycleOwner());
            ((StoreActivity)requireActivity()).LetsSearch();
        });

        return view;
    }
}