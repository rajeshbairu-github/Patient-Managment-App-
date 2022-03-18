package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class doctors extends AppCompatActivity {

    RecyclerView rv_doctors;

    int[] doctors_photo ={R.drawable.boyone,R.drawable.boyone};
    String[] doctors_name ={"Dr. Nageswara Rao Koneti","Dr. Umesh T"};
    String[] specialist_in ={"Pediatric Cardiologist, Hyderabad, India","Neurologist, Hyderabad, India"};
    String[] experience ={"Senior Consultant, 26 years of experience","Consultant, 27 years of experience"};
    String[] current_hospital ={" RAINBOW CHILDREN'S HOSPITAL, HYDERABAD","CONTINENTAL HOSPITALS, HYDERABAD"};
    String[] timings ={"8 AM To 11 AM\n6 PM to 8 PM","10 PM To 12 PM\n 9 PM TO 11 PM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_doctors=findViewById(R.id.doctors_rv);
        rv_doctors.setFocusable(false);
        doctors_adapter adapter=new doctors_adapter(this,doctors_photo,doctors_name,specialist_in,experience,current_hospital,timings);
        rv_doctors.setAdapter(adapter);
        rv_doctors.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));

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