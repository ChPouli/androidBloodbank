package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.Donor;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class DonorSearch extends AppCompatActivity {

private int iduser;
    private int iddonor;

    private EditText ATD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_search);
        Intent extraprev= getIntent();
        iduser=extraprev.getIntExtra("userid",0);
        ATD=(EditText)findViewById(R.id.SearchDonoreditText2);


    }
    public  void createSession(View view){
        Intent intent = new Intent(DonorSearch.this, Session.class);
        intent.putExtra("userid",iduser);
        intent.putExtra("donorid",iddonor);

        startActivity(intent);
    }

    public void searchOneDonor(View view){

        APIServiceAdapter.getInstance().searchDonor(ATD.getText().toString()).subscribe(new Subscriber<List<Donor>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("paparia", e.getMessage());
            }

            @Override
            public void onNext(List<Donor> donors) {
                if (donors.size() > 0) {
                        iddonor=donors.get(0).getId();
                    Toast.makeText(DonorSearch.this, donors.get(0).getFname() + " "
                            + iduser + " " + donors.get(0).getId(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(DonorSearch.this, "No Donor",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
