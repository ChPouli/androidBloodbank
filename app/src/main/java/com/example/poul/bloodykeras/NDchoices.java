package com.example.poul.bloodykeras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class NDchoices extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int iduser;
    private Intent intent;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndchoices);

        //kodikos user apo prohgoymeni o8oni
        Intent idintent = getIntent();
        iduser=idintent.getIntExtra("userid",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navdonor) {
            Toast.makeText(NDchoices.this, "ola kala " + " " + iduser ,Toast.LENGTH_LONG).show();
                //pigeno o8oni anazitisis doti kai toy klhrwnomw to id tou user
            Intent intent = new Intent(this, DonorSearch.class);
            intent.putExtra("userid",iduser);
            this.startActivity(intent);


        } else if (id == R.id.navpatient) {

            Toast.makeText(NDchoices.this, "ola kala " + " " + iduser ,Toast.LENGTH_LONG).show();
            //pigeno o8oni anazitisis patient kai toy klhrwnomw to id tou user
            intent = new Intent(this, PatientSearch.class);
            intent.putExtra("userid",iduser);
            this.startActivity(intent);

        } else if (id == R.id.navupdatebag) {

             intent = new Intent(this, UpdateBag.class);
            startActivity(intent);

        } else if (id == R.id.navrequests) {
             intent = new Intent(this, ApplicationScreen.class);
            startActivity(intent);

        } else if (id == R.id.navpie){
            intent = new Intent(this, PieChartScreen.class);
            startActivity(intent);


        } else if ( id == R.id.navscannfc){


        } else if (id == R.id.navthistory){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
