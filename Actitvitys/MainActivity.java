package com.example.aspecs.Actitvitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aspecs.R;
import com.example.aspecs.utils.Sharedpref;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Sharedpref sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = new Sharedpref(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.checkKeyExists("key_email")&&sp.getString("key_email")!=null&& !sp.getString("key_email").equalsIgnoreCase("")){
                    Intent i = new Intent(MainActivity.this, Homepage.class);
                    startActivity(i);
                }else {
                    Intent i = new Intent(MainActivity.this, Loginpage.class);
                    startActivity(i);
                }
                finish();
            }
        },3000);
    }
}