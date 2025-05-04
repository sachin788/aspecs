package com.example.aspecs.Actitvitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.Adapters.ReadingAdapter;
import com.example.aspecs.R;
import com.example.aspecs.databinding.ActivityWomenreadingglasssesBinding;
import com.example.aspecs.models.Readingglassesmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Womenreadingglassses extends AppCompatActivity {
    private ReadingAdapter modelsAdapter;
    ActivityWomenreadingglasssesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        fetchreadglassesdata();
    }
    private void initialize(){
        binding = ActivityWomenreadingglasssesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.mToolbar.ivmenu.setImageResource(R.drawable.backarrow);
        binding.mToolbar.tvtitle.setText("Women Reading Glasses");
    }
    private void fetchreadglassesdata(){
        FirebaseRecyclerOptions<Readingglassesmodel> options1 =
                new FirebaseRecyclerOptions.Builder<Readingglassesmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("ReadingglassesforWomen"), Readingglassesmodel.class)
                        .build();

        modelsAdapter = new ReadingAdapter(options1);
        binding.rvwomenreadglass.setAdapter(modelsAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        modelsAdapter.startListening();
        binding.rvwomenreadglass.getRecycledViewPool().clear();
        modelsAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStop() {
        super.onStop();
        modelsAdapter.stopListening();
    }
}