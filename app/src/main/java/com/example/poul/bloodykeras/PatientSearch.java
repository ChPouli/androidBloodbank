package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Service.APIService;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class PatientSearch extends AppCompatActivity {

    private EditText ATPa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search);

        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        PatientSearch.this.finish();
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

                    Toast.makeText(PatientSearch.this, patients.get(0).getLname() + " "+
                                                        patients.get(0).getAT() + " "
                                                        + Integer.toString(patients.size()),
                                    Toast.LENGTH_SHORT).show();

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
