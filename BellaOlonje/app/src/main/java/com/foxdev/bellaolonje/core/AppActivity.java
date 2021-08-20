package com.foxdev.bellaolonje.core;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.foxdev.bellaolonje.viewmodel.AppViewModel;

public abstract class AppActivity extends AppCompatActivity {

    protected static final int hideSystemUIValue = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_FULLSCREEN;

    protected AppViewModel appViewModel;

    public static final String categoriesName = "Categories";
}
