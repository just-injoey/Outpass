package com.example.may.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.may.Domain.DomainOutpass;
import com.example.may.R;

import java.util.ArrayList;

public class OutpassAdapter extends RecyclerView.Adapter<OutpassAdapter.ViewHolder> {
    ArrayList<DomainOutpass> domainOutpass;

    public OutpassAdapter(ArrayList<DomainOutpass> domainOutpass) {
        this.domainOutpass = domainOutpass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_outpass,parent,false);
        return new ViewHolder(inflate) ;
    }

    @SuppressLint("ResourceType") //added this to remove error in Color.parseColor() method
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(domainOutpass.get(position).getStatus()=="Approved"){
//            holder.status.setText(Color.parseColor("#4BE026"));
            holder.status.setTextColor(Color.rgb(75,224,38));
        } else if(domainOutpass.get(position).getStatus()=="Denied") {
//            holder.status.setText(Color.parseColor("#E93333"));
            holder.status.setTextColor(Color.rgb(233,51,51));
        }else {
//            holder.status.setText(Color.parseColor("#FFB901"));
            holder.status.setTextColor(Color.rgb(255,185,1));
        }

        holder.dateGen.setText(domainOutpass.get(position).getDateGen());
        holder.place.setText(domainOutpass.get(position).getToPlace());
        holder.status.setText(domainOutpass.get(position).getStatus());
        holder.opNum.setText(domainOutpass.get(position).getOid());



    }


    @Override
    public int getItemCount() {
        return domainOutpass.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateGen, place, status, opNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateGen = itemView.findViewById(R.id.tvDateGen);
            place = itemView.findViewById(R.id.tvPlace);
            status = itemView.findViewById(R.id.tvStatus);
            opNum = itemView.findViewById(R.id.tvOpNum);

        }
    }
}
