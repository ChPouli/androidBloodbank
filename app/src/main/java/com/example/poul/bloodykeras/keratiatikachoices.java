package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class keratiatikachoices extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

   private int iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keratiatikachoices);
        Intent idintent = getIntent();
        iduser=idintent.getIntExtra("userid",0);

    }
    public void karamba(View view){
        // Intent intent = new Intent(this, LoginScreen.class);
        Intent intent = new Intent(this, Session.class);
        startActivity(intent);

    }

    public void updatebagScreen(View view){
        // Intent intent = new Intent(this, LoginScreen.class);
        Intent intent = new Intent(this, UpdateBag.class);
        startActivity(intent);

    }
    public void applicationScreen(View view){
        // Intent intent = new Intent(this, LoginScreen.class);
        Intent intent = new Intent(this, ApplicationScreen.class);
        startActivity(intent);

    }
    public void showKeratiatikoMenu(View view){
        PopupMenu tragio = new PopupMenu(this,view);
        tragio.setOnMenuItemClickListener(this);
        //MenuInflater diforo = tragio.getMenuInflater();
        //diforo.inflate(R.menu.mymenu, tragio.getMenu());
        tragio.inflate(R.menu.mymenu);
        tragio.show();
    }
    public void showPatientMenu(View view){

        PopupMenu tragio = new PopupMenu(this,view);
        tragio.setOnMenuItemClickListener(this);
        tragio.inflate(R.menu.menupatient);
        tragio.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.keratiatiko:
               Toast.makeText(keratiatikachoices.this, "ola kala " + " " + iduser ,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, DonorSearch.class);
                intent.putExtra("userid",iduser);
                this.startActivity(intent);
                return true;
            case R.id.keratiatiko1:

                intent = new Intent(this, DonorProperties.class);
                this.startActivity(intent);
                return true;
            case R.id.Patientchoice1:
                intent = new Intent(this, PatientSearch.class);
                this.startActivity(intent);
                return true;
            case R.id.Patientchoice2:
                intent = new Intent(this, PatientScreen.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
