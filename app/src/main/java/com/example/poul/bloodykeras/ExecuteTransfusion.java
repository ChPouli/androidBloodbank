package com.example.poul.bloodykeras;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.poul.bloodykeras.Adapters.ResultDonorSearch;
import com.example.poul.bloodykeras.Adapters.TransusionBagsRVAdapter;
import com.example.poul.bloodykeras.Model.Bloodbag;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;

public class ExecuteTransfusion extends AppCompatActivity {
    private int iduser;
    private int idapply;
    private String appAbo;
    private int bloodunits;
    private String appRh;
    private String appkind;
    ArrayList<Bloodbag> list = new ArrayList<Bloodbag>();
    ArrayList<Bloodbag> sublist = new ArrayList<Bloodbag>();
    RecyclerView recyclerView;
    TransusionBagsRVAdapter adapter;
    CoordinatorLayout coordinatorLayout;
    NfcAdapter nfcAdapter;
    byte[] uid;
    int posGreenRV=333;
    int iter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_transfusion);

        //pairnei id user apo prohgoymenh activity
        Intent extraprev= getIntent();
        iduser=extraprev.getIntExtra("userid",0);
        idapply=extraprev.getIntExtra("applyid", 0);
        appAbo=extraprev.getStringExtra("ABOapp");
        bloodunits=extraprev.getIntExtra("unitsblood", 0);
        appRh=extraprev.getStringExtra("rhapp");
        appkind=extraprev.getStringExtra("kindapp");

        //nfc adapter
        nfcAdapter= NfcAdapter.getDefaultAdapter(this);

        //nfc enable or disable check
        if(nfcAdapter !=null && nfcAdapter.isEnabled()){
            // Toast.makeText(NewBag.this,"nfc available ",Toast.LENGTH_LONG).show();

        }
        else {
            finish();

        }

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


    //region byte array to hex string converter
    public static String byteArrayToHexString(final byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }
    //endregion



    @Override
    protected void onNewIntent(Intent intent) {


        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            // Toast.makeText(this, "NfcIntent!", Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //pairnw to byte array me to uid
            uid = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
/*gia perissotera nfc
            for (int k = 0; k <= (sublist.size() - 1); k++) {
                if ( byteArrayToHexString(uid).equals(sublist.get(0).getTagRfid())){

                    Toast.makeText(ExecuteTransfusion.this,"success",Toast.LENGTH_LONG).show();
                }
            }*/


            if ( byteArrayToHexString(uid).equals(sublist.get(0).getTagRfid())){

                Toast.makeText(ExecuteTransfusion.this, "success", Toast.LENGTH_SHORT).show();
                iter=iter-1;
                }
            else {
                Toast.makeText(ExecuteTransfusion.this, "Try other bag", Toast.LENGTH_SHORT).show();
            }



            //call gia na kanoume to available=0 sth bash,na desmeysoyme toys askoys dhladh
            if (iter==0) {

                Snackbar.make(coordinatorLayout, "Proceed to execute transfusion", Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // finish();

                APIServiceAdapter.getInstance().ExecTrans(sublist.get(0).getTagRfid()).subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                           // Toast.makeText(ExecuteTransfusion.this,"completed",Toast.LENGTH_LONG).show();

                    }
                });
                        Snackbar.make(coordinatorLayout, "Trans completed", Snackbar.LENGTH_INDEFINITE)
                                .show();
                    }
                }).show();
            }


        }
        super.onNewIntent(intent);
    }



    @Override
    protected void onResume() {

        enableForegroundDispatchSystem();
        super.onResume();
    }

    @Override
    protected void onPause() {
        disableForegroundDispatchSystem();
        super.onPause();
    }

    //region nf enable disable functions
    private void enableForegroundDispatchSystem(){
        Intent intent= new Intent( ExecuteTransfusion.this,ExecuteTransfusion.class);
        intent.addFlags(intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentfilter= new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentfilter, null);

    }
    private void disableForegroundDispatchSystem(){
        nfcAdapter.disableForegroundDispatch(this);
    }
    //endregion


    /*Sunartisi gia to koympi "check availability" An de brei eparkes apothema proballei mnm adynamias kalypsis
    * diaforetika fernei me recycler view th lista twn askwn poy apaitoyntai kata seira palaiothtas.
    * meta apo th probolh bohthitikwn mnm mesw Toast kai snack bar */

    public void checkAvailability(View view){
        //Toast.makeText(ExecuteTransfusion.this,"gamiesai",Toast.LENGTH_LONG).show();


        APIServiceAdapter.getInstance().gamhsou().subscribe(new Subscriber<List<Bloodbag>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Bloodbag> bloodbagss) {

                //h list ka8e fora krataei poses mpoykales yparxoyn gia na ikanopoihsoyn thn metaggish
                int l;
                for (l = 0; l <= (bloodbagss.size() - 1); l++) {

                    if (bloodbagss.get(l).getBloodtype().equals(appAbo) && bloodbagss.get(l).getRh().equals(appRh) && bloodbagss.get(l).getKind().equals(appkind)
                            ) {

                        list.add(bloodbagss.get(l));
                    }

                }


                if (list.size() == 0) {
                    Toast.makeText(ExecuteTransfusion.this, "There is no reserve of that type in bloodbank", Toast.LENGTH_LONG).show();
                } else if (list.size() < bloodunits) {
                    Toast.makeText(ExecuteTransfusion.this, "There is no sufficient reserve", Toast.LENGTH_LONG).show();
                } else if (list.size() >= bloodunits) {

                    for (int k = 0; k <= (bloodunits - 1); k++) {
                        sublist.add(list.get(k));
                    }

                    iter=bloodunits;
                    //Na brei to sygkekrimeno coordinator sto layout Mh to Xehaseis
                    coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

                    Toast toast= Toast.makeText(ExecuteTransfusion.this, "To execute transfusion,please scan with your phone the Bags trying first the oldest", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    //Snack bar
                    Snackbar.make(coordinatorLayout, "Begin Scanning", Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           // finish();
                           Snackbar.make(coordinatorLayout, "Scan bags until all items in list get green", Snackbar.LENGTH_INDEFINITE)
                                    .show();
                        }
                    }).show();



                    // Toast.makeText(ExecuteTransfusion.this,"enough reserve" + list.get(0).getTagRfid(),Toast.LENGTH_LONG).show();
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerviewTransBag);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ExecuteTransfusion.this));
                    adapter = new TransusionBagsRVAdapter(sublist, R.layout.item_transfusion_bloodbags, getApplicationContext(), iduser);
                    recyclerView.setAdapter(adapter);








                }


                // Toast.makeText(ExecuteTransfusion.this,bloodbagss.size()+"desto"+ list.size()+ list.get(0).getKind(),Toast.LENGTH_LONG).show();

            }
        });



    }


}
