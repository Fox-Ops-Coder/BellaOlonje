package com.foxdev.bellaolonje.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.activities.CoreActivity;

public final class OnboardingScreen extends Fragment {

    public OnboardingScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newView = inflater
                .inflate(R.layout.fragment_onboarding_screen, container, false);

        newView.findViewById(R.id.getStartedButton).setOnClickListener(v -> ((CoreActivity)requireActivity()).AppLoaded());

        return newView;
    }
}