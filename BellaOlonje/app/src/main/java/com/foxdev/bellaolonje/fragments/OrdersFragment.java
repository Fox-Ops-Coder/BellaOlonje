package com.foxdev.bellaolonje.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.activities.StoreActivity;

public final class OrdersFragment extends Fragment {

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        View.OnClickListener onClickListener = v -> ((StoreActivity)requireActivity())
                .ReturnToStore();

        view.findViewById(R.id.orderBackButton).setOnClickListener(onClickListener);

        view.findViewById(R.id.startOrderingButton).setOnClickListener(onClickListener);

        return view;
    }
}