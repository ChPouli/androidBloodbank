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
import com.example.poul.bloodykeras.Model.Donor;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.R;
import com.example.poul.bloodykeras.Session;

import java.util.List;

import rx.Observable;



public class PatientRVadapter extends RecyclerView.Adapter<PatientRVadapter.PatientSearchViewHolder> {

    private List<Patient> patients;
    private int rowLayout;
    private Context context;
    private int idpatient;
    private int iduser;


    public PatientRVadapter(List<Patient> patients, int rowLayout, Context context,int idpatient, int iduser) {
        this.patients =patients ;
        this.rowLayout = rowLayout;
        this.context = context;
        this.idpatient= idpatient;
        this.iduser=iduser;
    }

    @Override
    public PatientRVadapter.PatientSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);



        return new PatientSearchViewHolder(view);
    }

    int row_index;
    @Override
    public void onBindViewHolder(PatientSearchViewHolder holder, final int position) {

        holder.lname.setText( patients.get(position).getLname() );
        holder.fname.setText( patients.get(position).getFname() );
        holder.fathername.setText( "Father's name : " + " " + patients.get(position).getFathername());
        holder.at.setText("AT: " + " " + patients.get(position).getAT());
        holder.phone.setText("Phone : " + " " + patients.get(position).getPhone());


        //γαμημένος onclick listener της recycle view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context = v.getContext();

                Intent intent = new Intent(context, ApplicationScreen.class);

                intent.putExtra("userid",iduser);
                intent.putExtra("patientid", idpatient);

                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();


    }

    public static class PatientSearchViewHolder extends RecyclerView.ViewHolder {
        TextView lname;
        TextView fname;
        TextView at;
        TextView fathername;
        TextView phone;


        public PatientSearchViewHolder(View v) {
            super(v);

            lname = (TextView) v.findViewById(R.id.ResultPatientLname);
            fname = (TextView) v.findViewById(R.id.ResultPatientFname);
            at = (TextView) v.findViewById(R.id.ResultPatientAT);
            fathername = (TextView) v.findViewById(R.id.ResultPatientFathername);
            phone = (TextView) v.findViewById(R.id.ResultPatientPhone);

        }

        /*@Override
        public void onClick(View v) {

            //Toast.makeText(DonorSearchViewHolder.this,"la",Toast.LENGTH_LONG).show();
        }*/

    }
}


