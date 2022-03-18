package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class patient_home extends AppCompatActivity {

    ImageView iv_logout;

    RecyclerView rv_doctors_timings;

    ScrollView scrollView;

    Intent aboutme_intent;

    Snackbar snackbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        View layout=findViewById(R.id.mainscroll);
        snackbar=snackbar.make(layout,"Loading ...",Snackbar.LENGTH_INDEFINITE);



        TextView tvName=findViewById(R.id.name);  //change
        tvName.setText("Hi , "+patientData.name);   //change

        Intent mainactivity_intent=new Intent(this,MainActivity.class);

        scrollView=findViewById(R.id.mainscroll);
        scrollView.fullScroll(ScrollView.FOCUS_UP);

        iv_logout=findViewById(R.id.logout_button);

        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomDialogClass customDialogClass=new CustomDialogClass(patient_home.this);
                customDialogClass.show();

            }
        });

        LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;

        linearLayout1=findViewById(R.id.aboutme_layout);
        linearLayout2=findViewById(R.id.myreports_layout);
        linearLayout3=findViewById(R.id.mymedicines_layout);
        linearLayout4=findViewById(R.id.other_layout);

        aboutme_intent=new Intent(this,about_me.class);
        Intent myreports_intent=new Intent(this,myreports.class);
        Intent mymedicines_intent=new Intent(this,mymedicines.class);
        Intent surgery_intent=new Intent(this,surgery.class);


        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snackbar.show();

                FetchData();
               // Toast.makeText(getApplicationContext(),"About Me Fragment",Toast.LENGTH_SHORT).show();
            }

        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(myreports_intent);

              //  Toast.makeText(getApplicationContext(),"My Reports Fragment",Toast.LENGTH_SHORT).show();
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mymedicines_intent);
               // Toast.makeText(getApplicationContext(),"My Medicines Fragment",Toast.LENGTH_SHORT).show();
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(surgery_intent);
              //  Toast.makeText(getApplicationContext(),"Other Fragment",Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout l_callforappointment,l_doctors,l_feedback,l_abouthospital;

        Intent callforappointment_intent=new Intent(this,call_for_appointment.class);
        Intent doctors_intent=new Intent(this,doctors.class);
        Intent feedback_intent=new Intent(this,feedback.class);
        Intent abouthospital_intent=new Intent(this,About_hospital.class);


        l_callforappointment=findViewById(R.id.callforappointment_layout);
        l_doctors=findViewById(R.id.doctors_layout);
        l_feedback=findViewById(R.id.feedback_layout);
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

        l_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(feedback_intent);
              //  Toast.makeText(getApplicationContext(),"Feedback",Toast.LENGTH_SHORT).show();
            }
        });

        l_abouthospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(abouthospital_intent);
             //   Toast.makeText(getApplicationContext(),"About Hospital",Toast.LENGTH_SHORT).show();
            }
        });

        int doctors_photos[]={R.drawable.boyone,R.drawable.boyone};
        String []doctors_name={"Kohli Kohli","Dhoni Dhoni"};
        String[] timings={"08 AM To 09 AM\n06 PM To 09 PM","09 AM To 11 AM\n03 PM To 06 PM"};

       rv_doctors_timings=findViewById(R.id.doctors_timings_rv);
       rv_doctors_timings.setFocusable(false);
        doctors_timings_adapter adapter=new doctors_timings_adapter(this,doctors_photos,doctors_name,timings);
        rv_doctors_timings.setAdapter(adapter);
        rv_doctors_timings.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    void FetchData()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://dorian-works.000webhostapp.com/pateintHealthStatus.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            String error_value=jsonObject.getString("error");

                            if(error_value.equals("false"))
                            {
                                patientData.phone=jsonObject.getString("phone");
                                patientData.sufferingWith=jsonObject.getString("sufferingWith");
                                patientData.dateOfVisits=jsonObject.getString("dateOfVisits");
                                snackbar.dismiss();
                                startActivity(aboutme_intent);

                            }
                            else
                            {   snackbar.dismiss();
                                Toast.makeText(patient_home.this,"SomeThing Went Wrong...\n Please try again after sometime",Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) {

                           // e.printStackTrace();
                            snackbar.dismiss();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("phone",patientData.phone);

                return  params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}