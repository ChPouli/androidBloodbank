package com.example.poul.bloodykeras;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.poul.bloodykeras.Adapters.ExpiredBagsRVadapter;
import com.example.poul.bloodykeras.Adapters.ResultDonorSearch;
import com.example.poul.bloodykeras.Model.Application;
import com.example.poul.bloodykeras.Model.Bloodbag;
import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;

public class NDchoices extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int iduser;
    private Intent intent;
    String fixdate;
    String gamhdi;
    RecyclerView recyclerView;
    ExpiredBagsRVadapter adapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndchoices);

        //kodikos user apo prohgoymeni o8oni
        Intent idintent = getIntent();
        iduser=idintent.getIntExtra("userid",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        APIServiceAdapter.getInstance().getOlikoBlood().subscribe(new Subscriber<List<Bloodbag>>() {
            @Override
            public void onCompleted() {
               // Toast.makeText(NDchoices.this, "Ti 8a ginei?complete", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NDchoices.this, "Ti 8a ginei? error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Bloodbag> bloodbags) {


                recyclerView = (RecyclerView) findViewById(R.id.recyclerviewNDchoices);
                recyclerView.setLayoutManager(new LinearLayoutManager(NDchoices.this));
                adapter = new ExpiredBagsRVadapter(bloodbags, R.layout.item_expired_bags, getApplicationContext(), iduser);
                recyclerView.setAdapter(adapter);


               // Toast.makeText(NDchoices.this, "Ti 8a ginei?" + gamhdi, Toast.LENGTH_LONG).show();

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(NDchoices.this, ScanNFC.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer   = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       //prin htan setdrawerlistener
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//to ekana private panw
        NavigationView  navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ndchoices, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();


        //log out handling by alert box
        if (id == R.id.logout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure,You want to log out?");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                   finish();

                                }
                            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                            return;
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navdonor) {
           // Toast.makeText(NDchoices.this, "ola kala " + " " + iduser ,Toast.LENGTH_LONG).show();
                //pigeno o8oni anazitisis doti kai toy klhrwnomw to id tou user
            Intent intent = new Intent(this, DonorSearch.class);
            intent.putExtra("userid",iduser);

            this.startActivity(intent);


        } else if (id == R.id.navpatient) {

            //Toast.makeText(NDchoices.this, "ola kala " + " " + iduser ,Toast.LENGTH_LONG).show();
            //pigeno o8oni anazitisis patient kai toy klhrwnomw to id tou user
            intent = new Intent(this, PatientSearch.class);
            intent.putExtra("userid",iduser);
            this.startActivity(intent);

        } else if (id == R.id.navupdatebag) {

             intent = new Intent(this, UpdateBag.class);
            startActivity(intent);

        } else if (id == R.id.navrequests) {
            intent = new Intent(this, TransRequests.class);
            intent.putExtra("userid",iduser);
            startActivity(intent);



        } else if (id == R.id.navpie){
            intent = new Intent(this, PieChartScreen.class);
            startActivity(intent);


        } else if ( id == R.id.navscannfc){
            intent = new Intent(this, ScanNFC.class);
            startActivity(intent);


        } else if (id == R.id.navthistory){

        }
        else if(id ==R.id.navadddonor){
            intent = new Intent(this, DonorProperties.class);
            startActivity(intent);
        }
        else if (id==R.id.navaddpatient){
            intent = new Intent(this, PatientScreen.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
