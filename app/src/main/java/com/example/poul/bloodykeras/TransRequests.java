package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.poul.bloodykeras.Adapters.ApplicationRVadapter;
import com.example.poul.bloodykeras.Adapters.PatientRVadapter;
import com.example.poul.bloodykeras.Model.Application;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class TransRequests extends AppCompatActivity {
    private int iduser;
    RecyclerView recyclerView;
    ApplicationRVadapter adapter;
    CoordinatorLayout CoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_requests);
        //pairnei id user apo prohgoymenh activity
        Intent extraprev= getIntent();
        iduser=extraprev.getIntExtra("userid",0);
        APIServiceAdapter.getInstance().getAllApplications().subscribe(new Subscriber<List<Application>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("skata", e.getMessage());
            }

            @Override
            public void onNext(List<Application> applications) {

                recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPendingApplications);
                recyclerView.setLayoutManager(new LinearLayoutManager(TransRequests.this));
                adapter = new ApplicationRVadapter(applications, R.layout.item_pending_applications, getApplicationContext() ,iduser);
                recyclerView.setAdapter(adapter);

            }
        });

        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
/*
    public void pendingRequests(View view){

        APIServiceAdapter.getInstance().getAllApplications().subscribe(new Subscriber<List<Application>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("skata", e.getMessage());
            }

            @Override
            public void onNext(List<Application> applications) {

                recyclerView = (RecyclerView) findViewById(R.id.recyclerviewPendingApplications);
                recyclerView.setLayoutManager(new LinearLayoutManager(TransRequests.this));
                adapter = new ApplicationRVadapter(applications, R.layout.item_pending_applications, getApplicationContext() ,iduser);
                recyclerView.setAdapter(adapter);
            }
        });
    }*/
}
