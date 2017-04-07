package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.SessionM;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class Session extends AppCompatActivity {
    private int iduser;
    private int iddonor;

    private EditText pressure;
    private EditText hbht;
    private EditText reactions;
    private EditText status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        Intent getextras=getIntent();
        iduser=getextras.getIntExtra("userid",0);
        iddonor=getextras.getIntExtra("donorid",0);

        pressure= (EditText)findViewById(R.id.PressureEditText);
        hbht=(EditText)findViewById(R.id.aimatokritiEditText);
        reactions=(EditText)findViewById(R.id.reactionsEditText);
        status=(EditText)findViewById(R.id.sessionstatusEditText);

    }

    public  void addSession(View view){
        //Toast.makeText(Session.this,"user"+ iduser + " " + "donor"+ iddonor, Toast.LENGTH_LONG).show();

            //Toast.makeText(Session.this,"mphkes gamhdi?",Toast.LENGTH_LONG).show();
        APIServiceAdapter.getInstance()
                .addSession(pressure.getText().toString(), hbht.getText().toString(), status.getText().toString(),reactions.getText().toString(),iduser,iddonor)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("gamhdi1", e.getMessage());
                    }

                    @Override
                    public void onNext(Void aVoid) {
                                APIServiceAdapter.getInstance().getSessionId(iddonor).subscribe(new Subscriber<List<SessionM>>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("gamhdi2", e.getMessage());
                                    }

                                    @Override
                                    public void onNext(List<SessionM> sessionMs) {
                                        Toast.makeText(Session.this,"panagia moy" + sessionMs.get(0).getId(),Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });
                        /*(new Subscriber<List<SessionM>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("gamhmenoKerato", e.getMessage());
                    }

                    @Override
                    public void onNext(List<SessionM> sessionMs) {

                       Toast.makeText(Session.this,sessionMs.get(0).getId(),Toast.LENGTH_LONG).show();
                    }
                });*/
    }



    public void createBag(View view){
        Intent intent = new Intent(this, NewBag.class);
        startActivity(intent);
    }
}
