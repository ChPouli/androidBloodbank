package com.example.poul.bloodykeras.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.poul.bloodykeras.Model.Bloodbag;

import com.example.poul.bloodykeras.R;

import java.util.List;

/**
 * Created by poul on 29/9/2017.
 */
public class ExpiredBagsRVadapter extends RecyclerView.Adapter<ExpiredBagsRVadapter.ExpiredBagsViewHolder>  {

    private List<Bloodbag> bloodbags;
    private int rowLayout;
    private Context context;

    private int iduser;


    public ExpiredBagsRVadapter(List<Bloodbag> bloodbags, int rowLayout, Context context, int iduser) {
        this.bloodbags =bloodbags ;
        this.rowLayout = rowLayout;
        this.context = context;

        this.iduser=iduser;
    }

    @Override
    public ExpiredBagsRVadapter.ExpiredBagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);



        return new ExpiredBagsViewHolder(view);
    }

    int row_index;
    @Override
    public void onBindViewHolder(ExpiredBagsViewHolder holder, final int position) {

        holder.UID.setText("Tag's id: "+ bloodbags.get(position).getTagRfid() );
        holder.product.setText("Blood product:"+" "+bloodbags.get(position).getKind() );
        holder.btype.setText("Blood type : " + " " + bloodbags.get(position).getBloodtype());
        holder.rh.setText("Rh: " + " " + bloodbags.get(position).getRh());
        holder.prodDate.setText("produced Date:" + " " + bloodbags.get(position).getDate());
        holder.prodDate.setBackgroundColor(Color.parseColor("#8BFC0800"));




        //γαμημένος onclick listener της recycle view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return bloodbags.size();


    }

    public static class ExpiredBagsViewHolder extends RecyclerView.ViewHolder {
        TextView UID;
        TextView product;
        TextView btype;
        TextView rh;
        TextView prodDate;



        public ExpiredBagsViewHolder(View v) {
            super(v);

            UID = (TextView) v.findViewById(R.id.UIDExpiredBagsRV);
            product = (TextView) v.findViewById(R.id.productExpiredBagsRV);
            btype = (TextView) v.findViewById(R.id.BloodTypeExpiredBagsRV);
            rh = (TextView) v.findViewById(R.id.RhExpiredBagsRV);
           prodDate=(TextView) v.findViewById(R.id.DateProducedExpiredBagsRV);

        }



    }
}
