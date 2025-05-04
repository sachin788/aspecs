package com.example.aspecs.Actitvitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aspecs.Adapters.ModelsAdapterdemo;
import com.example.aspecs.Fragments.Kids_products_fragment;
import com.example.aspecs.Fragments.Men_products_fragment;
import com.example.aspecs.Fragments.women_products_fragment;
import com.example.aspecs.R;
import com.example.aspecs.databinding.ActivityHomepageBinding;
import com.example.aspecs.models.Modelimages;
import com.example.aspecs.utils.Sharedpref;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
    private ActivityHomepageBinding binding;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private ModelsAdapterdemo modelsAdapter;
    Sharedpref sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize_and_onclicks();
        Readingglasses();
        newframesslider();
        fetchuserdata();
    }
    //all onclicks and initializers
    private void initialize_and_onclicks(){
        sp = new Sharedpref(this);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.f_productframelayout, Men_products_fragment.class, null)
                .commit();
        binding.mToolbar.tvtitle.setText("Aspecs");
        binding.bHomeMen.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.f_productframelayout, Men_products_fragment.class, null)
                    .commit();
        });
        binding.bHomeWomen.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.f_productframelayout, women_products_fragment.class, null)
                    .commit();
        });
        binding.bHomeKids.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.f_productframelayout, Kids_products_fragment.class, null)
                    .commit();
        });
        binding.mToolbar.ivmenu.setOnClickListener(view -> {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });
        binding.mToolbar.ivsearch.setOnClickListener(view -> {
            Intent i = new Intent(this,Search_activity.class);
            startActivity(i);
        });
        binding.mToolbar.ivCart.setOnClickListener(view -> {
            Intent i = new Intent(this,Cartactivity.class);
            startActivity(i);
        });
        binding.sideView.tvLogout.setOnClickListener(view -> {
            auth.signOut();
            sp.clearData();
            Intent i = new Intent(this,Loginpage.class);
            startActivity(i);
        });

    }
    //new release of frames
    private void newframesslider(){
        final List<SlideModel> slideModelList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Sliderpics").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    slideModelList.add(new SlideModel(String.valueOf((data.child("sliderimage").getValue())), ScaleTypes.CENTER_CROP));
                    binding.isnewglasses.setImageList(slideModelList, ScaleTypes.CENTER_CROP);
                    Log.d("sadasdasd", "" + data.child("sliderimage").getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //Reading glasses firebase
    private void Readingglasses(){
        FirebaseRecyclerOptions<Modelimages> options =
                new FirebaseRecyclerOptions.Builder<Modelimages>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Modelpics"),Modelimages.class)
                        .build();

        modelsAdapter = new ModelsAdapterdemo(options);
        binding.rvmodelimages.setAdapter(modelsAdapter);
    }
    //userdata like name, email, profile pics.
    private void fetchuserdata(){
        //User firebase
        String userid = auth.getCurrentUser().getUid();
        Log.d("useridd",""+userid);
        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();
        DocumentReference documentReference = firestore.collection("User").document(userid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Bitmap bitmap = BitmapFactory.decodeFile(documentSnapshot.getString("profileimage"));
                binding.sideView.ivUserImage.setImageBitmap(bitmap);
                binding.sideView.tvUserName.setText(documentSnapshot.getString("Username"));
                binding.sideView.tvUserEmail.setText(documentSnapshot.getString("email"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Homepage.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        modelsAdapter.startListening();
        binding.rvmodelimages.getRecycledViewPool().clear();
        modelsAdapter.notifyDataSetChanged();
        modelsAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        modelsAdapter.stopListening();
    }
}