package com.example.aspecs.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.aspecs.Actitvitys.Meneyeglasses_activity;
import com.example.aspecs.databinding.FragmentMenProductsFragmentBinding;

public class Men_products_fragment extends Fragment {

FragmentMenProductsFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMenProductsFragmentBinding.inflate(getLayoutInflater(), container, false);
        singleclick();
        return binding.getRoot();
    }
    private void singleclick(){
        binding.clmenEyeglasses.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), Meneyeglasses_activity.class);
            startActivity(i);
        });

    }
}