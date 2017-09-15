package com.example.poul.bloodykeras.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poul.bloodykeras.ExecuteTransfusion;
import com.example.poul.bloodykeras.Model.Application;
import com.example.poul.bloodykeras.Model.Bloodbag;
import com.example.poul.bloodykeras.R;

import java.util.ArrayList;
import java.util.List;
public class TransusionBagsRVAdapter extends RecyclerView.Adapter<TransusionBagsRVAdapter.TransBloodbagsViewHolder> {

    private ArrayList<Bloodbag> Tbloodbag;
    private int rowLayout;
    private Context context;
    private int idpatient;
    private int iduser;


    private String DayProduced;




    public TransusionBagsRVAdapter (ArrayList<Bloodbag> Tbloodbag, int rowLayout, Context context, int iduser) {
        this.Tbloodbag =Tbloodbag ;
        this.rowLayout = rowLayout;
        this.context = context;

        this.iduser=iduser;

    }

    @Override
    public TransusionBagsRVAdapter.TransBloodbagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);



        return new TransBloodbagsViewHolder(view);
    }

    int row_index;
    @Override
    public void onBindViewHolder(TransBloodbagsViewHolder holder, final int position) {

        holder.uid.setText("BloodBag uid:" + " " + Tbloodbag.get(position).getTagRfid() );
        holder.prodDate.setText("Produced Date:" + " " + Tbloodbag.get(position).getDate());





    }

    @Override
    public int getItemCount() {
        return Tbloodbag.size();


    }

    public static class TransBloodbagsViewHolder extends RecyclerView.ViewHolder {
        TextView uid;
        TextView prodDate;



        public TransBloodbagsViewHolder(View v) {
            super(v);

            uid = (TextView) v.findViewById(R.id.UIDTransBloodbagsRV);
            prodDate=(TextView) v.findViewById(R.id.DateProducedBloodbagsRV);

        }


    }
}


