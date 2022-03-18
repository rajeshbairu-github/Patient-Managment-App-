package com.example.hospital_try_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class doctors_timings_adapter extends RecyclerView.Adapter<doctors_timings_adapter.MyViewHolder>{

    Context c;
    int[] item_doctors_photo;
    String[] item_doctors_name;
    String[] item_timings;

    public doctors_timings_adapter(Context ct,int []doctors_photo,String[] doctors_name,String[] timings)
    {
        c=ct;
        item_doctors_photo=doctors_photo;
        item_doctors_name=doctors_name;
        item_timings=timings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view=inflater.inflate(R.layout.doctors_timing_list_layout,parent,false);
        return new doctors_timings_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.iv_doctors_photo.setImageResource(item_doctors_photo[position]);
        holder.tv_doctors_name.setText(item_doctors_name[position]);
        holder.tv_timings.setText(item_timings[position]);

    }

    @Override
    public int getItemCount() {
        return item_doctors_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_doctors_photo;

        TextView tv_doctors_name,tv_timings;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           iv_doctors_photo=itemView.findViewById(R.id.doctors_photo_roundedimag);
           tv_doctors_name=itemView.findViewById(R.id.doctors_name_tv);
           tv_timings=itemView.findViewById(R.id.timings_tv);


        }
    }
}
