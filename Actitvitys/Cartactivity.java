package com.example.aspecs.Actitvitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.Adapters.CartAdapter;
import com.example.aspecs.R;
import com.example.aspecs.databinding.ActivityCartactivityBinding;
import com.example.aspecs.models.Readingglassesmodel;
import com.example.aspecs.utils.Sharedpref;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Cartactivity extends AppCompatActivity {
    ActivityCartactivityBinding binding;
    FirebaseFirestore firestore;
    ArrayList<Readingglassesmodel> modellist;
    CartAdapter adapter;
    Sharedpref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        fetchreadglassesdata();
    }

    private void initialize() {
        sp = new Sharedpref(this);
        binding = ActivityCartactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.mToolbar.ivmenu.setImageResource(R.drawable.backarrow);
        binding.mToolbar.tvtitle.setText("Cart");
        modellist = new ArrayList<>();
        adapter = new CartAdapter(modellist, this);
        binding.rvcartdata.setAdapter(adapter);
    }

    private void fetchreadglassesdata() {
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Cartdetails").whereEqualTo("email",sp.getString("key_email")).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot data : list) {
                            Readingglassesmodel obj = data.toObject(Readingglassesmodel.class);
                                modellist.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}