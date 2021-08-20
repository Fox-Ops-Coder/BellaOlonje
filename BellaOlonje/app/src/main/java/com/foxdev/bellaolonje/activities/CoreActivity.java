package com.foxdev.bellaolonje.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.core.AppActivity;
import com.foxdev.bellaolonje.fragments.LaunchFragment;
import com.foxdev.bellaolonje.fragments.OnboardingScreen;
import com.foxdev.bellaolonje.objects.Category;
import com.foxdev.bellaolonje.viewmodel.AppViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public final class CoreActivity extends AppActivity {

    private static final int hideSystemUIValue = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_FULLSCREEN;

    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        LaunchFragment launchFragment = new LaunchFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentPlaceholder, launchFragment, "launchScreen")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        appViewModel = new ViewModelProvider(this)
                .get(AppViewModel.class);

        appViewModel.GetCategoriesLiveData().observe(this, categories -> {
            if (categories == null) {
                new AlertDialog.Builder(this)
                        .setTitle("Внимание")
                        .setMessage("Отсутвует подключение к интернету")
                        .create()
                        .show();
            } else {
                CompleteLoad();
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(hideSystemUIValue);

        appViewModel.LoadCategories();
    }

    public void AppLoaded() {
        appViewModel.GetCategoriesLiveData().removeObservers(this);

        Category[] categories = appViewModel.GetCategoriesLiveData().getValue();

        Intent intent = new Intent(this, StoreActivity.class);
        intent.putExtra(categoriesName, categories);

        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        finish();
    }

    private void CompleteLoad() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPlaceholder, new OnboardingScreen())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}