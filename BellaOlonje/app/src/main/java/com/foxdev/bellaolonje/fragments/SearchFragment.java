package com.foxdev.bellaolonje.fragments;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.activities.StoreActivity;
import com.foxdev.bellaolonje.adapters.MealsAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public final class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        MealsAdapter mealsAdapter = new MealsAdapter();
        RecyclerView mealsList = view.findViewById(R.id.searchedMealsList);
        mealsList.setAdapter(mealsAdapter);

        StoreActivity storeActivity = (StoreActivity) requireActivity();

        storeActivity.GetMealLiveData().observe(getViewLifecycleOwner(), mealsAdapter::SetFood);

        EditText editText = (EditText)view.findViewById(R.id.searchText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                storeActivity.SearchMeals(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.post(() -> {
            InputMethodManager inputMethodManager=
                    (InputMethodManager)requireContext()
                            .getSystemService(INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;

            inputMethodManager.toggleSoftInputFromWindow(
                    editText.getWindowToken(),InputMethodManager.SHOW_IMPLICIT, 0);

            editText.requestFocus();
        });

        view.findViewById(R.id.searchBackButton).setOnClickListener(v -> {
            storeActivity.GetMealLiveData().removeObservers(getViewLifecycleOwner());
            editText.clearFocus();

            ((StoreActivity)requireActivity()).ReturnToStore();
        });

        return view;
    }
}