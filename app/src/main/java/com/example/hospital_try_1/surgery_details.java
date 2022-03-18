package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class surgery_details extends AppCompatActivity {

    TextView tv_surgeryname,tv_do_surgery,tv_surgery_description;

    String surgery_name,surgery_date,surgeryDescription,surgeryBefore,surgeryAfter;

    ImageView imageView_before_surgery,imageView_after_surgery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgery_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_surgeryname=findViewById(R.id.surgeryname_tv);
        tv_do_surgery=findViewById(R.id.do_surgery_tv);
        tv_surgery_description=findViewById(R.id.surgery_description_tv);

        surgery_name=getIntent().getExtras().getString("surgery_name");
        surgery_date=getIntent().getExtras().getString("surgery_date");
        surgeryDescription=getIntent().getExtras().getString("surgeryDescription");
        surgeryBefore=getIntent().getExtras().getString("surgeryBefore");
        surgeryAfter=getIntent().getExtras().getString("surgeryAfter");


        tv_surgeryname.setText(surgery_name);
        tv_do_surgery.setText(surgery_date);
        tv_surgery_description.setText(surgeryDescription);

        imageView_before_surgery=findViewById(R.id.before_surgery);
        imageView_after_surgery=findViewById(R.id.after_surgery);

        Picasso.with(this)
                .load(surgeryBefore)
                .into(imageView_before_surgery);
        Picasso.with(this)
                .load(surgeryAfter)
                .into(imageView_after_surgery);

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