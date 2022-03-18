package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class myreports extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private reports_adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    AutoCompleteTextView autoCompleteTextView_report_search;

    String url="https://firebasestorage.googleapis.com/v0/b/try1-e88fc.appspot.com/o/9696596966.pdf?alt=media&token=ed9f201a-2d23-4a11-8a61-3ddeae8d85e4";

    //String[] date_o_v_reports ={"21-11-2020","26-11-2020","15-12-2020","10-01-2021","11-01-2021","12-01-2021"};

   // String []pdf_links={url,url,url,url,url,url};

    String[] date_o_v_reports;
    String []pdf_links;

    ArrayList<String> dateOfReport=new ArrayList<String>();
    ArrayList<String> reportLink=new ArrayList<String>();


    ArrayList<String> arrayList_dates=new ArrayList<String>();

    ImageView imageView_search;

    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreports);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View layout=findViewById(R.id.layout);

        snackbar=snackbar.make(layout,"Loading ...", Snackbar.LENGTH_INDEFINITE);
        

        snackbar.show();
        FetchData();

      //  createExampleList();
      //  buildRecyclerView();


    }

    void textSetter()
    {
       // Toast.makeText(getApplicationContext(),"Text Setter",Toast.LENGTH_SHORT).show();

        imageView_search=findViewById(R.id.search_icon);
        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Search",Toast.LENGTH_SHORT).show();
            }
        });

        //arrayList_dates.addAll(Arrays.asList(date_o_v_reports));

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,dateOfReport);

        autoCompleteTextView_report_search=findViewById(R.id.reports_search);
        autoCompleteTextView_report_search.setAdapter(arrayAdapter);

        //   autoCompleteTextView_report_search.setAdapter(date_o_v_reports);

        autoCompleteTextView_report_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    void FetchData()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://dorian-works.000webhostapp.com/patientReport.php",
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

                                dateOfReport.add(JO.getString("reportDate"));
                                reportLink.add(JO.getString("reportLink"));

                              //  date_o_v_reports[count]=JO.getString("reportDate");
                             //  pdf_links[count]=JO.getString("reportLink");

                              count++;
                            }

                            snackbar.dismiss();
                            textSetter();
                            createExampleList();
                            buildRecyclerView();

                            //Toast.makeText(getApplicationContext(),"Data Fetched",Toast.LENGTH_LONG).show();

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


    private void filter(String text) {
        ArrayList<ExampleItem> filteredList = new ArrayList<>();
        for (ExampleItem item : mExampleList) {

            if (item.getMdate().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }

        }
        mAdapter.filterList(filteredList);
    }

    private void createExampleList() {

      //  Toast.makeText(getApplicationContext(),"Example List",Toast.LENGTH_SHORT).show();

        mExampleList = new ArrayList<>();

        for(int i=0;i<dateOfReport.size();i++)
        {
            mExampleList.add(new ExampleItem(dateOfReport.get(i),reportLink.get(i)));
        }

        /*for(int i=0;i<date_o_v_reports.length;i++)
        {
            mExampleList.add(new ExampleItem(date_o_v_reports[i],pdf_links[i]));
        }*/

    }

    private void buildRecyclerView() {
       // Toast.makeText(getApplicationContext(),"Recycler Biuld",Toast.LENGTH_SHORT).show();

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new reports_adapter(this,mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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