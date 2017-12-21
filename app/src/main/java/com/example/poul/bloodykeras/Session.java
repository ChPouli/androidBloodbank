package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.SessionM;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class Session extends AppCompatActivity {
    private int iduser;
    private int iddonor;
    private int idsession;
    String status;
    private EditText pressure;
    private EditText hbht;
    private EditText reactions;

    CheckBox chkBoxStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent getextras=getIntent();
        iduser=getextras.getIntExtra("userid",0);
        iddonor=getextras.getIntExtra("donorid",0);

        pressure= (EditText)findViewById(R.id.PressureEditText);
        hbht=(EditText)findViewById(R.id.aimatokritiEditText);
        reactions=(EditText)findViewById(R.id.reactionsEditText);

        chkBoxStatus = (CheckBox) findViewById(R.id.checkBoxStatus);
       chkBoxStatus.setChecked(false);

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
        Session.this.finish();
    }
    //endregion

    public  void addSession(View view){

        if (chkBoxStatus.isChecked()==true){

        status="0";
            APIServiceAdapter.getInstance()
                    .addSession(pressure.getText().toString(), hbht.getText().toString(), status, reactions.getText().toString(), iduser, iddonor)
                    .subscribe(new Subscriber<Void>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Void aVoid) {
                            Toast.makeText(Session.this, "Session successfully inserted" , Toast.LENGTH_LONG).show();
                                finish();
                        }
                    });

        }

        else {

            status="1";

            APIServiceAdapter.getInstance()
                    .addSession(pressure.getText().toString(), hbht.getText().toString(), status, reactions.getText().toString(), iduser, iddonor)
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

                                    // Toast.makeText(Session.this,"panagia moy" + sessionMs.get(0).getId(),Toast.LENGTH_LONG).show();
                                    idsession = sessionMs.get(0).getId();

                                    Toast toast = Toast.makeText(Session.this, "Session successfully inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();
                                    Intent intent = new Intent(Session.this, NewBag.class);
                                    intent.putExtra("userid", iduser);
                                    intent.putExtra("donorid", iddonor);
                                    intent.putExtra("sessionid", idsession);

                                    startActivity(intent);

                                    finish();
                                }
                            });
                        }
                    });
        }

    }




}
