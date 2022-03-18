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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class doctors_adapter extends RecyclerView.Adapter<doctors_adapter.MyViewHolder>{

    Context c;
    int[] item_doctors_photo;
    String[] item_doctors_name;
    String[] item_specialist_in;
    String[] item_experience;
    String[] item_current_hospital;
    String[] item_timings;

    private int mExpandedPosition = -1;
    int previousExpandedPosition=-1;

    public doctors_adapter(Context ct,int[] doctors_photo,String[] doctors_name,String[] specialist_in,String[] experience,String[] current_hospital,String[] timings) {
        c=ct;
        item_doctors_photo=doctors_photo;
        item_doctors_name=doctors_name;
        item_specialist_in=specialist_in;
        item_experience=experience;
        item_current_hospital=current_hospital;
        item_timings=timings;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view=inflater.inflate(R.layout.doctors_adpater_layout,parent,false);
        return new doctors_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.iv_doctors_photo.setImageResource(item_doctors_photo[position]);
        holder.tv_doctors_name.setText(item_doctors_name[position]);
        holder.tv_experience.setText(item_experience[position]);
        holder.tv_current_hospital.setText(item_current_hospital[position]);
        holder.tv_timings.setText(item_timings[position]);

        final boolean isExpanded=position==mExpandedPosition;

        holder.llExpandArea.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.iv_down_bt.setActivated(isExpanded);

        if(isExpanded)
        { previousExpandedPosition=position;
            holder.iv_down_bt.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            //Toast.makeText(c,"Position "+position+"is expanded",Toast.LENGTH_SHORT).show();
        }
        else
        {   holder.iv_down_bt.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            //Toast.makeText(c,"No position is expanded",Toast.LENGTH_SHORT).show();
        }

        holder.iv_down_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition=isExpanded?-1:position;

                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item_doctors_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_doctors_photo,iv_down_bt;

        TextView tv_doctors_name,tv_experience,tv_current_hospital,tv_timings;

        LinearLayout llExpandArea;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_doctors_photo=itemView.findViewById(R.id.doctors_photo_roundedimag);
            tv_doctors_name=itemView.findViewById(R.id.doctors_name);
            tv_experience=itemView.findViewById(R.id.experience_tv);
            tv_current_hospital=itemView.findViewById(R.id.current_hospital_tv);
            tv_timings=itemView.findViewById(R.id.timings_tv);

            iv_down_bt=itemView.findViewById(R.id.down_button);

            llExpandArea=itemView.findViewById(R.id.doctor_dropdown);



        }
    }
}
