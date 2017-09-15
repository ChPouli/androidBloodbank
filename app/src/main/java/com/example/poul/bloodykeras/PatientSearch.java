package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Adapters.PatientRVadapter;
import com.example.poul.bloodykeras.Adapters.ResultDonorSearch;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Service.APIService;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class PatientSearch extends AppCompatActivity {

    private EditText ATPa;
    private int iduser;
    private int idpatient;
    RecyclerView recyclerView;
    PatientRVadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);

        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //pairnei id user apo prohgoymenh activity
        Intent extraprev= getIntent();
        iduser=extraprev.getIntExtra("userid",0);
        ATPa=(EditText)findViewById(R.id.SearchPatienteditText);
    }
    //region toolbar back arrow control functions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion

    public void searchOnePatient(View view){
        APIServiceAdapter.getInstance().searchPatient(ATPa.getText().toString()).subscribe(new Subscriber<List<Patient>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Patient> patients) {
                if (patients.size() > 0) {

                    idpatient=patients.get(0).getId();

                    recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPatientSearch);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PatientSearch.this));
                    adapter = new PatientRVadapter(patients, R.layout.item_patient_search, getApplicationContext(),idpatient ,iduser);
                    recyclerView.setAdapter(adapter);

                    }
                else
                    Toast.makeText(PatientSearch.this, "No Patient",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showAllPatients(View view){

        Log.e("skatoules", "lla");
        APIServiceAdapter.getInstance().getPatients().subscribe(new Subscriber<List<Patient>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("skata", e.getMessage());
            }

            @Override
            public void onNext(List<Patient> patients) {
                Toast.makeText(PatientSearch.this,patients.get(1).getFname() + " "
                        + Integer.toString(patients.size()),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
