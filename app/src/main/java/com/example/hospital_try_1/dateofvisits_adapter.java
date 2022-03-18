package com.example.hospital_try_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class dateofvisits_adapter extends RecyclerView.Adapter<dateofvisits_adapter.MyViewHolder>{

    Context c;
    String item_dov[];

    String num_names[]={"First Vist Was On ","Second Vist Was On ","Third Vist Was On ","Fourth Vist Was On ","Fifth Vist Was On ","Sixth Vist Was On ","Seventh Vist Was On ","Eighth Vist Was On ","Ninth Vist Was On ","Tenth Vist Was On "};

    public dateofvisits_adapter(Context ct, String dov[]) {
        c=ct;
        item_dov=dov;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view=inflater.inflate(R.layout.dov_layout,parent,false);
        return new dateofvisits_adapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_title.setText(num_names[position]);

        //adding elements for last
      //  holder.tv_dov.setText(item_dov[getItemCount() - 1 -position]);
        holder.tv_dov.setText(item_dov[position]);
    }

    @Override
    public int getItemCount() {
        return item_dov.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_dov,tv_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_dov=itemView.findViewById(R.id.dov_tv);
            tv_title=itemView.findViewById(R.id.title_tv);

        }
    }
}
