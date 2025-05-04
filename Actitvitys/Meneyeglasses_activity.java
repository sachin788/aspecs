package com.example.aspecs.Actitvitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.databinding.ActivityMeneyeglassesBinding;

public class Meneyeglasses_activity extends AppCompatActivity {
ActivityMeneyeglassesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeneyeglassesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}