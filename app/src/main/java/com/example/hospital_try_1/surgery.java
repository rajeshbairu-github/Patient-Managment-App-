package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class surgery extends AppCompatActivity {

    RecyclerView rv_surgery_list;

    String[] surgery_names ={"Heart Bypass Surgery","Brain Tumor"};
    String[] surgery_date ={"15-12-2020","10-01-2021"};

    ArrayList<String> surgeryNames=new ArrayList<String>();
    ArrayList<String> surgeryDate=new ArrayList<String>();
    ArrayList<String> surgeryDescription=new ArrayList<String>();
    ArrayList<String> surgeryBefore=new ArrayList<String>();
    ArrayList<String> surgeryAfter=new ArrayList<String>();

    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View layout=findViewById(R.id.layout);

        snackbar=snackbar.make(layout,"Loading ...", Snackbar.LENGTH_INDEFINITE);

        snackbar.show();
        FetchDate();


    }

    void buildRecyclerView()
    {
        rv_surgery_list=findViewById(R.id.surgery_list);
        rv_surgery_list.setFocusable(false);
        surgery_list_adapter adapter=new surgery_list_adapter(this,surgeryNames,surgeryDate, surgeryDescription,surgeryBefore,surgeryAfter);
        rv_surgery_list.setAdapter(adapter);
        rv_surgery_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    void FetchDate()
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://dorian-works.000webhostapp.com/patientSurgery.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(getApplicationContext(),"Fetching",Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject;
                            JSONArray jsonArray;

                            jsonObject=new JSONObject(response);
                            jsonArray=jsonObject.getJSONArray("server_response");
                            int count=0;

                            while(count<jsonArray.length())
                            {
                                JSONObject JO=jsonArray.getJSONObject(count);

                                surgeryNames.add(JO.getString("surgeryName"));
                                surgeryDate.add(JO.getString("dateOfSurgery"));
                                surgeryDescription.add(JO.getString("surgeryDescription"));
                                surgeryBefore.add(JO.getString("beforeSurgery"));
                                surgeryAfter.add(JO.getString("afterSurgery"));

                               // dateOfReport.add(JO.getString("reportDate"));
                               // reportLink.add(JO.getString("reportLink"));

                                //  date_o_v_reports[count]=JO.getString("reportDate");
                                //  pdf_links[count]=JO.getString("reportLink");

                                count++;
                            }


                            snackbar.dismiss();
                            buildRecyclerView();

                           // Toast.makeText(getApplicationContext(),"Data Fetched",Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {

                            snackbar.dismiss();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                            // e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                snackbar.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
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