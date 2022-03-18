package com.example.hospital_try_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class home_recyclerview_adapter extends RecyclerView.Adapter<home_recyclerview_adapter.MyViewHolder>{

    Context c;
    String item_n[];
    int item_img[];

    public home_recyclerview_adapter(Context ct,String n[],int img[]) {
        c=ct;
        item_n=n;
        item_img=img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view=inflater.inflate(R.layout.layout_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_title.setText(item_n[position]);
        holder.imageView.setImageResource(item_img[position]);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,"You clicked "+item_n[position],Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return item_img.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {  ImageView imageView;
       TextView tv_title;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            tv_title=itemView.findViewById(R.id.title);
            linearLayout=itemView.findViewById(R.id.list_layout);

        }
    }
}
