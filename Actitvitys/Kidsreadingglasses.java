package com.example.aspecs.Actitvitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.Adapters.ReadingAdapter;
import com.example.aspecs.R;
import com.example.aspecs.databinding.ActivityKidsreadingglassesBinding;
import com.example.aspecs.models.Readingglassesmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Kidsreadingglasses extends AppCompatActivity {
    private ActivityKidsreadingglassesBinding binding;
    private ReadingAdapter modelsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        fetchreadglassesdata();
    }
    private void initialize(){
        binding = ActivityKidsreadingglassesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.mToolbar.ivmenu.setImageResource(R.drawable.backarrow);
        binding.mToolbar.tvtitle.setText("Women Reading Glasses");
    }
    private void fetchreadglassesdata(){
        FirebaseRecyclerOptions<Readingglassesmodel> options1 =
                new FirebaseRecyclerOptions.Builder<Readingglassesmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("ReadingglassesforKids"), Readingglassesmodel.class)
                        .build();

        modelsAdapter = new ReadingAdapter(options1);
        binding.rvkidsreadglass.setAdapter(modelsAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        modelsAdapter.startListening();
        binding.rvkidsreadglass.getRecycledViewPool().clear();
        modelsAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStop() {
        super.onStop();
        modelsAdapter.stopListening();
    }
}