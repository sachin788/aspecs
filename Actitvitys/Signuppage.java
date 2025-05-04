package com.example.aspecs.Actitvitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.databinding.ActivitySignuppageBinding;
import com.example.aspecs.models.Usermodel;
import com.example.aspecs.utils.Sharedpref;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signuppage extends AppCompatActivity {
     ActivitySignuppageBinding binding;
     private FirebaseAuth auth;
     private FirebaseFirestore firestore;
     private ProgressDialog progressDialog;
     private String imagepath;
     private Uri imageUri;
     private Sharedpref sp;
    private static final int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = new Sharedpref(this);
        binding = ActivitySignuppageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        binding.ivprofile.setOnClickListener(view -> {
            openGallery();
        });

        binding.btnsignup.setOnClickListener(view -> {
            String Email = binding.etemail.getText().toString().trim();
            String Password = binding.etpassword.getText().toString();
            String Mobileno = binding.etmobileno.getText().toString();
            String Username = binding.etusername.getText().toString();
            Log.d("pathh",imagepath);

            progressDialog.show();
            auth.createUserWithEmailAndPassword(Email,Password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(Signuppage.this, Loginpage.class));
                            progressDialog.cancel();
                            //sharepref
//                            sp.setString("key_email",Email);

                            firestore.collection("User")
                                    .document(FirebaseAuth.getInstance().getUid())
                                    .set(new Usermodel(Email, Mobileno, imagepath,Username));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signuppage.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                              progressDialog.cancel();
                        }
                    });



        });
    }



    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            binding.ivprofile.setImageURI(imageUri);
            imagepath = uriToStringConvert(imageUri);
            Log.d("asdas", "" + imagepath);

        }
    }

    private String uriToStringConvert(Uri newUri) {

        String path;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = this.getContentResolver().query(newUri,
                filePathColumn, null, null, null);

        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(
                filePathColumn[0]);
        path = cursor.getString(columnIndex);
        cursor.close();
        return path;
    }
}