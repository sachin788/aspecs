package com.example.aspecs.Actitvitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.Adapters.ReadingAdapter;
import com.example.aspecs.R;
import com.example.aspecs.databinding.ActivityMenreadingglassesBinding;
import com.example.aspecs.models.Readingglassesmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Menreadingglasses extends AppCompatActivity {
    private ActivityMenreadingglassesBinding binding;
    private ReadingAdapter modelsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         initialize();
        fetchreadglassesdata();
    }
    private void initialize(){
        binding = ActivityMenreadingglassesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.mToolbar.ivmenu.setImageResource(R.drawable.backarrow);
        binding.mToolbar.tvtitle.setText("Men Reading Glasses");
    }
    private void fetchreadglassesdata(){
        FirebaseRecyclerOptions<Readingglassesmodel> options1 =
                new FirebaseRecyclerOptions.Builder<Readingglassesmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Readingglassesformen"), Readingglassesmodel.class)
                        .build();

        modelsAdapter = new ReadingAdapter(options1);
        binding.rvmenreadglass.setAdapter(modelsAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        modelsAdapter.startListening();
        binding.rvmenreadglass.getRecycledViewPool().clear();
        modelsAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStop() {
        super.onStop();
        modelsAdapter.stopListening();
    }
}