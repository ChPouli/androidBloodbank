package com.example.poul.bloodykeras.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poul.bloodykeras.ApplicationScreen;
import com.example.poul.bloodykeras.ExecuteTransfusion;
import com.example.poul.bloodykeras.Model.Application;
import com.example.poul.bloodykeras.Model.Donor;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.R;
import com.example.poul.bloodykeras.Session;

import java.util.List;

import rx.Observable;



public class ApplicationRVadapter extends RecyclerView.Adapter<ApplicationRVadapter.PendingApplicationsViewHolder> {

    private List<Application> applications;
    private int rowLayout;
    private Context context;
    private int idpatient;
    private int iduser;
    private int idapply;
    private int bloodunits;
    private String appAbo;
    private String appRh;
    private String appkind;



    public ApplicationRVadapter (List<Application> applications, int rowLayout, Context context, int iduser) {
        this.applications =applications ;
        this.rowLayout = rowLayout;
        this.context = context;

        this.iduser=iduser;
    }

    @Override
    public ApplicationRVadapter.PendingApplicationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);



        return new PendingApplicationsViewHolder(view);
    }

    int row_index;
    @Override
    public void onBindViewHolder(PendingApplicationsViewHolder holder, final int position) {

        holder.id.setText( "Request id:" + " " +applications.get(position).getId() );
        holder.product.setText("Request Product:" + " " +applications.get(position).getEidosParagogou() );
        holder.quantity.setText( "Quantity : " + " " + applications.get(position).getQuantity());
        holder.priority.setText("priority: " + " " + applications.get(position).getPriority());
        holder.transdate.setText("Transfusion Date : " + " " + applications.get(position).getTransfusionDate());
        holder.abo.setText("ABO: " + " " + applications.get(position).getABO());
        holder.rh.setText("Rh : " + " " + applications.get(position).getRh());


        //γαμημένος onclick listener της recycle view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idapply=applications.get(position).getId();
                appAbo=applications.get(position).getABO();
                bloodunits=applications.get(position).getQuantity();
                appRh=applications.get(position).getRh();
                appkind=applications.get(position).getEidosParagogou();

                context = v.getContext();

                Intent intent = new Intent(context, ExecuteTransfusion.class);

                intent.putExtra("userid",iduser);
                intent.putExtra("applyid",idapply);
                intent.putExtra("ABOapp",appAbo);
                intent.putExtra("unitsblood",bloodunits);
                intent.putExtra("rhapp",appRh);
                intent.putExtra("kindapp",appkind);


                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return applications.size();


    }

    public static class PendingApplicationsViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView product;
        TextView quantity;
        TextView priority;
        TextView transdate;
        TextView abo;
        TextView rh;


        public PendingApplicationsViewHolder(View v) {
            super(v);

            id = (TextView) v.findViewById(R.id.PendAppId);
            product = (TextView) v.findViewById(R.id.PendAppParagogo);
            quantity = (TextView) v.findViewById(R.id.PendAppQuantity);
            priority = (TextView) v.findViewById(R.id.PendAppPriority);
            transdate = (TextView) v.findViewById(R.id.PendAppTransDate);
            abo = (TextView) v.findViewById(R.id.PendAppABO);
            rh = (TextView) v.findViewById(R.id.PendAppRh);


        }

        /*@Override
        public void onClick(View v) {

            //Toast.makeText(DonorSearchViewHolder.this,"la",Toast.LENGTH_LONG).show();
        }*/

    }
}


