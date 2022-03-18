package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Loding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        Intent patient_home_intent=new Intent(this,patient_home.class);
        Intent mainactivity_intent=new Intent(this,MainActivity.class);

        if(SaveSharedPreference.getUserName(this).length()==0)
        {
            startActivity(mainactivity_intent);
            finish();
        }
        else
        {
            startActivity(patient_home_intent);
            finish();
           // Toast.makeText(this,"Old user "+SaveSharedPreference.getUserName(getApplicationContext()),Toast.LENGTH_SHORT).show();
        }
    }
}