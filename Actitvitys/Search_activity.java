package com.example.aspecs.Actitvitys;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.Adapters.Treadingbrandsadapter;
import com.example.aspecs.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

public class Search_activity extends AppCompatActivity {
       ActivitySearchBinding binding;
       Treadingbrandsadapter adapter,adapter2;
       String[] searchlist= new String[]{"Air Lock","Adidas","Baellon","Bally","Biggu","Cavier",
       "Cover Girl","Denim","Dior","Diccilo","Gant","Nike","Polo","Stark","Stark","Stark"};
       List<String> brandslist;
    List<String> brandslist2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        brandslist = new ArrayList<>();
        brandslist2 = new ArrayList<>();
        brandslist.add("Air Lock");
        brandslist.add("Adidas");
        brandslist.add("Alain Mikli");
        brandslist.add("Baeloon");
        brandslist.add("Bally");
        brandslist.add("Biggu");
        brandslist.add("Cavier");
        brandslist.add("Cover Girl");
        brandslist.add("Denim");
        brandslist.add("Dior");
        brandslist.add("Diccilo");
        brandslist.add("Gant");
        brandslist2.add("Modo");
        brandslist2.add("Polo");
        brandslist2.add("Nike");
        brandslist2.add("Qakley");
        brandslist2.add("Stark");
        adapter = new Treadingbrandsadapter(brandslist,this);
        adapter2 = new Treadingbrandsadapter(brandslist2,this);
        binding.rvtrendingbrands.setAdapter(adapter);
        binding.rvtrendingbrands2.setAdapter(adapter2);
        ArrayAdapter<String> searchlistt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,searchlist);
        binding.acSearch.setAdapter(searchlistt);
    }
}