package com.example.hospital_try_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class surgery_list_adapter extends RecyclerView.Adapter<surgery_list_adapter.MyViewHolder>{

    Context c;
  //  String[] item_surgery_names;
   // String[] item_surgery_date;

    ArrayList<String> surgeryNames=new ArrayList<String>();
    ArrayList<String> surgeryDate=new ArrayList<String>();
    ArrayList<String> surgeryDescription=new ArrayList<String>();
    ArrayList<String> surgeryBefore=new ArrayList<String>();
    ArrayList<String> surgeryAfter=new ArrayList<String>();


    public surgery_list_adapter(Context ct, ArrayList<String> surgeryNames,ArrayList<String> surgeryDate,ArrayList<String> surgeryDescription,ArrayList<String> surgeryBefore,ArrayList<String> surgeryAfter)
    {
        c=ct;
        this.surgeryNames=surgeryNames;
        this.surgeryDate=surgeryDate;
        this.surgeryDescription=surgeryDescription;
        this.surgeryBefore=surgeryBefore;
        this.surgeryAfter=surgeryAfter;

       // item_surgery_names=surgery_names;
       // item_surgery_date=surgery_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view=inflater.inflate(R.layout.surgery_list_layout,parent,false);
        return new surgery_list_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_surgery_title.setText(surgeryNames.get(position));

        Intent surgery_details_intent=new Intent(c,surgery_details.class);

        holder.bt_view_surgery_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                surgery_details_intent.putExtra("surgery_name",surgeryNames.get(position));
                surgery_details_intent.putExtra("surgery_date",surgeryDate.get(position));
                surgery_details_intent.putExtra("surgeryDescription",surgeryDescription.get(position));
                surgery_details_intent.putExtra("surgeryBefore",surgeryBefore.get(position));
                surgery_details_intent.putExtra("surgeryAfter",surgeryAfter.get(position));

                c.startActivity(surgery_details_intent);

              //  Toast.makeText(c,"You clicked "+item_surgery_names[position],Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return surgeryNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_surgery_title;
        Button bt_view_surgery_details;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_surgery_title=itemView.findViewById(R.id.surgery_title);
            bt_view_surgery_details=itemView.findViewById(R.id.view_surgery_details);


        }
    }
}
