package com.example.aspecs.Actitvitys;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aspecs.databinding.ActivityProductdetailsBinding;
import com.example.aspecs.utils.Sharedpref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Productdetails_activity extends AppCompatActivity {
      ActivityProductdetailsBinding binding;
      FirebaseFirestore firestore;
      Sharedpref sp;
      FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductdetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = new Sharedpref(this);

        binding.tvprice.setText( getIntent().getStringExtra("price"));
        binding.tvbrandname.setText( getIntent().getStringExtra("brandname"));
        binding.tvrating.setText(getIntent().getStringExtra("rating"));
        binding.tvsize.setText(getIntent().getStringExtra("size"));
        binding.tvdiscount.setText(getIntent().getStringExtra("discount"));
        binding.btnaddtocart.setOnClickListener(view -> {
           checkcartid();
        });
        List<SlideModel> modelList = new ArrayList<>();
        String image1 = getIntent().getStringExtra("image");
        String image2 = getIntent().getStringExtra("subimage1");
        String image3 = getIntent().getStringExtra("subimage2");
        String image4 = getIntent().getStringExtra("subimage3");
        modelList.add(new SlideModel(image1,ScaleTypes.CENTER_CROP));
        modelList.add(new SlideModel(image2,ScaleTypes.CENTER_CROP));
        modelList.add(new SlideModel(image3,ScaleTypes.CENTER_CROP));
        modelList.add(new SlideModel(image4,ScaleTypes.CENTER_CROP));
        binding.frameimage.setImageList(modelList, ScaleTypes.CENTER_CROP);
    }

    private void senddatatocart(){
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        String keyid = sp.getString("key_email");
        Toast.makeText(this, ""+keyid, Toast.LENGTH_SHORT).show();
        final Map<String,String> detaillist = new HashMap<>();
        detaillist.put("brandname",getIntent().getStringExtra("brandname"));
        detaillist.put("price",getIntent().getStringExtra("price"));
        detaillist.put("imageurl",getIntent().getStringExtra("image"));
        detaillist.put("discount",getIntent().getStringExtra("discount"));
        detaillist.put("size",getIntent().getStringExtra("size"));
        detaillist.put("rating",getIntent().getStringExtra("rating"));
        detaillist.put("email",keyid);
        detaillist.put("uid",getIntent().getStringExtra("uid"));


        firestore.collection("Cartdetails").add(detaillist).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Productdetails_activity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void checkcartid(){

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Cartdetails").whereEqualTo("uid",getIntent().getStringExtra("uid")).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            senddatatocart();

                        }else{
                            Toast.makeText(Productdetails_activity.this, "There", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}