package com.example.aspecs.Actitvitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.databinding.ActivityLoginpageBinding;
import com.example.aspecs.utils.Sharedpref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;

public class Loginpage extends AppCompatActivity {

    private boolean isBackpressedonce = false;
    ActivityLoginpageBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    private Sharedpref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        sp = new Sharedpref(this);
        firestore = FirebaseFirestore.getInstance();
        binding.btnlogin.setOnClickListener(view -> {
             String Email =binding.etemail.getText().toString().trim();
             String Password =binding.etpassword.getText().toString();


             if (Email.equals("")||Password.equals("")){
                 Toast.makeText(this, "Empty fields!", Toast.LENGTH_SHORT).show();
                 binding.tvwarning.setVisibility(View.VISIBLE);
             }else{
                 checkEmailExistsOrNot();
                 Toast.makeText(this, ""+FirebaseAuth.getInstance().getUid(), Toast.LENGTH_SHORT).show();
                 binding.tvwarning.setVisibility(View.INVISIBLE);
                 auth.signInWithEmailAndPassword(Email,Password)
                         .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                             @Override
                             public void onSuccess(AuthResult authResult) {

                                 Toast.makeText(Loginpage.this, "Login Success", Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(Loginpage.this,Homepage.class));
                             }
                         })
                         .addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(Loginpage.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         });

             }
         });

        binding.tvsignup.setOnClickListener(view -> {
            startActivity(new Intent(Loginpage.this, Signuppage.class));
        });

        binding.tvreset.setOnClickListener(view -> {
            String Email =binding.etemail.getText().toString().trim();

            auth.sendPasswordResetEmail(Email)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Loginpage.this, "Check your Email", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Loginpage.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    public void onBackPressed() {
        if(isBackpressedonce) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "Press again to exit!!", Toast.LENGTH_SHORT).show();
        isBackpressedonce=true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackpressedonce=false;
            }
        },2000);
    }
    void checkEmailExistsOrNot(){
        auth.fetchSignInMethodsForEmail(binding.etemail.getText().toString().trim()).addOnCompleteListener(
                new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                Log.d("idddddd",""+task.getResult().getSignInMethods().size());
                if (task.getResult().getSignInMethods().size() == 0){
                    // email not existed
                    Toast.makeText(Loginpage.this, "not there", Toast.LENGTH_SHORT).show();
                }else {
                    // email existed
                    Toast.makeText(Loginpage.this, "theree", Toast.LENGTH_SHORT).show();
                    sp.setString("key_email",binding.etemail.getText().toString().trim());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }
}