package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class mymedicines extends AppCompatActivity {

    LinearLayout l_morning,l_afternoon,l_evening,l_night;

    Button bt_prescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymedicines);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        l_morning=findViewById(R.id.morning_layout);
        l_afternoon=findViewById(R.id.afternoon_layout);
        l_evening=findViewById(R.id.evening_layout);
        l_night=findViewById(R.id.night_layout);

        Intent common_intent=new Intent(this,medicines_list.class);


        l_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                common_intent.putExtra("time","Morning");
                startActivity(common_intent);
            }
        });

        l_afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                common_intent.putExtra("time","Afternoon");
                startActivity(common_intent);
            }
        });

        l_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                common_intent.putExtra("time","Evening");
                startActivity(common_intent);
            }
        });

        l_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                common_intent.putExtra("time","Night");
                startActivity(common_intent);
            }
        });

        bt_prescription=findViewById(R.id.view_prescription_bt);

        Intent doctors_prescription_intent=new Intent(this,doctors_prescription.class);

        bt_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(doctors_prescription_intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}