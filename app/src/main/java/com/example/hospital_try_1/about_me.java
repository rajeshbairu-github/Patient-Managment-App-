package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class about_me extends AppCompatActivity {

    ImageView im_expand;

    TextView tvName,tvAge,tvBlood,tvPhone,tvSufferingwith;

    int expand_pos=0;

    RecyclerView rv_dateofvisits;

    String []date_o_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

       // Toast.makeText(getApplicationContext(),patientData.dateOfVisits,Toast.LENGTH_LONG).show();
        date_o_v=patientData.dateOfVisits.split(",");

        tvName=findViewById(R.id.name);
        tvAge=findViewById(R.id.age);
        tvBlood=findViewById(R.id.blood);
        tvPhone=findViewById(R.id.phone);
        tvSufferingwith=findViewById(R.id.suffering_with);

        tvName.setText(patientData.name);
        tvAge.setText(patientData.age);
        tvBlood.setText(patientData.blood);
        tvPhone.setText(patientData.phone);
        tvSufferingwith.setText(patientData.sufferingWith);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        im_expand=findViewById(R.id.down_button);


        rv_dateofvisits=findViewById(R.id.dov_list);
        dateofvisits_adapter adapter=new dateofvisits_adapter(this,date_o_v);
        rv_dateofvisits.setAdapter(adapter);
        rv_dateofvisits.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        rv_dateofvisits.setVisibility(View.GONE);

        im_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(expand_pos==0)
                {
                    im_expand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    rv_dateofvisits.setVisibility(View.VISIBLE);
                    expand_pos=1;
                }
                else {
                    im_expand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    rv_dateofvisits.setVisibility(View.GONE);
                    expand_pos=0;
                }

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