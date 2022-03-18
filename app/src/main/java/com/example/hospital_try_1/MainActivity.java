package com.example.hospital_try_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PatternMatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    Button bt_login;

    TextInputEditText tif_phone,tif_dob;

    Intent patient_home_intent;

    GifImageView gifImageView_loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tif_phone=findViewById(R.id.phone_tif);
        gifImageView_loader=findViewById(R.id.loader);
        gifImageView_loader.setVisibility(View.GONE);
       // tif_dob=findViewById(R.id.dob_tif);

       /* tif_dob.addTextChangedListener(new TextWatcher() {
            int len=0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String inp_dob= Objects.requireNonNull(tif_dob.getText()).toString();
                len=inp_dob.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inp_dob=tif_dob.getText().toString();
                if(inp_dob.length()==2&&len<inp_dob.length())
                {
                    tif_dob.append("-");
                }
                if(inp_dob.length()==5&&len<inp_dob.length())
                {
                    tif_dob.append("-");
                }

            }
        });


        */

        bt_login=findViewById(R.id.button_login);

       patient_home_intent=new Intent(this,patient_home.class);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String input_phone=tif_phone.getText().toString();
            //String input_dob=tif_dob.getText().toString();
            input_phone=input_phone.trim();
            //input_dob=input_dob.trim();

            check_inputs(input_phone);

            }
        });


        LinearLayout l_callforappointment,l_doctors,l_abouthospital;

        Intent callforappointment_intent=new Intent(this,call_for_appointment.class);
        Intent doctors_intent=new Intent(this,doctors.class);
        Intent abouthospital_intent=new Intent(this,About_hospital.class);


        l_callforappointment=findViewById(R.id.callforappointment_layout);
        l_doctors=findViewById(R.id.doctors_layout);
        l_abouthospital=findViewById(R.id.abouthospital_layout);

        l_callforappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(callforappointment_intent);
                //   Toast.makeText(getApplicationContext(),"Call For Appointment",Toast.LENGTH_SHORT).show();
            }
        });

        l_doctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(doctors_intent);
                // Toast.makeText(getApplicationContext(),"Doctors",Toast.LENGTH_SHORT).show();
            }
        });


        l_abouthospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(abouthospital_intent);
                //   Toast.makeText(getApplicationContext(),"About Hospital",Toast.LENGTH_SHORT).show();
            }
        });


    }



    void check_inputs(String input_phone)
    {
        if(input_phone.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Missing Fields",Toast.LENGTH_SHORT).show();
        }
        else {
            validate_inputs(input_phone);
        }

    }

    void validate_inputs(String input_phone)
    {
        bt_login.setEnabled(false);
        gifImageView_loader.setVisibility(View.VISIBLE);
        checkDataBase(input_phone);

       /* if(input_phone.equals("9959123549"))
        {
            gifImageView_loader.setVisibility(View.VISIBLE);
            SaveSharedPreference.setUserName(MainActivity.this,input_phone);
            startActivity(patient_home_intent);
            finish();
            gifImageView_loader.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
        }*/
    }

    void checkDataBase(String input_phone)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://dorian-works.000webhostapp.com/patientLogin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            String error_value=jsonObject.getString("error");

                            if(error_value.equals("false"))
                            {
                                patientData.phone=jsonObject.getString("phone");
                                patientData.name=jsonObject.getString("name");
                                patientData.age=jsonObject.getString("age");
                                patientData.blood=jsonObject.getString("blood");

                                bt_login.setEnabled(true);
                                SaveSharedPreference.setUserName(MainActivity.this,input_phone);
                                startActivity(patient_home_intent);
                                finish();
                                gifImageView_loader.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                            }
                            else
                            { bt_login.setEnabled(true);
                                gifImageView_loader.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) {
                            bt_login.setEnabled(true);
                            gifImageView_loader.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                          //  e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressDialog.hide();
                //student_login_b.setVisibility(View.VISIBLE);
               // progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("phone",input_phone);

                return  params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}
