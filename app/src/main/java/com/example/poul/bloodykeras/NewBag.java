package com.example.poul.bloodykeras;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import rx.Subscriber;

public class NewBag extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner typeSp, antipiktikoSp;
    private int iduser;
    private int iddonor;
    private int idsession;
    private  String  bagtype ,anti;
    private EditText volume;
    private  EditText nfc;
    String timeStamp;
    byte[] uid;
    StringBuilder builder;
    CoordinatorLayout coordinatorLayout;

    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bag);
        Intent getextras=getIntent();
        iduser=getextras.getIntExtra("userid",0);
        iddonor=getextras.getIntExtra("donorid",0);
        idsession=getextras.getIntExtra("sessionid",0);
        volume=(EditText)findViewById(R.id.volumenewbagEditText);


        //Na brei to sygkekrimeno coordinator sto layout Mh to Xehaseis
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutNewBag);

        Snackbar.make(coordinatorLayout, "After completing the fields,approach your device to NFC tag to finish insertion", Snackbar.LENGTH_INDEFINITE)
                .show();




        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        //spinner add
        addItemsOnSpinners();

        //nfc adapter
        nfcAdapter= NfcAdapter.getDefaultAdapter(this);

        //nfc enable or disable check
        if(nfcAdapter !=null && nfcAdapter.isEnabled()){
           // Toast.makeText(NewBag.this,"nfc available ",Toast.LENGTH_LONG).show();

                                                        }
        else {
            finish();

                }
    }

    //region byte array to hex string converter
    public static String byteArrayToHexString(final byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }
//endregion

    //pianei array kai grafei mnm
    @Override
    protected void onNewIntent(Intent intent) {


        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
           // Toast.makeText(this, "NfcIntent!", Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //pairnw to byte array me to uid
             uid = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);


            String rec= timeStamp +" "+  iddonor +" "+ volume.getText().toString()+ " "+anti;

            NdefMessage ndefMessage = createNdefMessage(rec);

            writeNdefMessage(tag, ndefMessage);

            //call gia add bloodbag
            APIServiceAdapter.getInstance().addNewBag(bagtype,anti,volume.getText().toString(),idsession, byteArrayToHexString(uid)).subscribe(new Subscriber<Void>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Void aVoid) {

                    Toast.makeText(NewBag.this, "Blood Bag succesfully inserted", Toast.LENGTH_LONG).show();
                    finish();

                }
            });



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

    //region Spinner handling

    public void addItemsOnSpinners(){
        typeSp = (Spinner) findViewById(R.id.BagsSpinner);
        antipiktikoSp = (Spinner) findViewById(R.id.antipiktikoSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterBag = ArrayAdapter.createFromResource(this,R.array.Bag_Type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterAnti = ArrayAdapter.createFromResource(this,R.array.Antipiktiko_Type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterBag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterAnti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSp.setAdapter(adapterBag);
        typeSp.setOnItemSelectedListener(this);
        antipiktikoSp.setAdapter(adapterAnti);
        antipiktikoSp.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
         bagtype = typeSp.getSelectedItem().toString();
        //String item = parent.getItemAtPosition(pos).toString();
         anti=antipiktikoSp.getSelectedItem().toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    //endregion


    //region nf enable disable functions
    private void enableForegroundDispatchSystem(){
        Intent intent= new Intent( NewBag.this,NewBag.class);
        intent.addFlags(intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentfilter= new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this,pendingIntent,intentfilter,null);

    }
    private void disableForegroundDispatchSystem(){
        nfcAdapter.disableForegroundDispatch(this);
    }
    //endregion



    //nfc format write functions
    private void formatTag(Tag tag, NdefMessage ndefMessage){

        try{
            NdefFormatable ndefFormatable= NdefFormatable.get(tag);

            if(ndefFormatable == null){
                Toast.makeText(NewBag.this,"Tag is not Ndef Formatable",Toast.LENGTH_LONG).show();
                return;
            }

            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();
           // Toast.makeText(NewBag.this,"Tag written",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.e("Format tag",e.getMessage());
        }

    }

    private void writeNdefMessage(Tag tag,NdefMessage ndefMessage){

        try{
            if (tag == null){
                Toast.makeText(NewBag.this,"Tag object cannot be null",Toast.LENGTH_LONG).show();
                return;
            }

            Ndef ndef= Ndef.get(tag);
            if (ndef==null){
                //format tag with ndef format and write message
                formatTag(tag,ndefMessage);
            }
            else {
                ndef.connect();
                if(!ndef.isWritable()){
                    Toast.makeText(NewBag.this,"Tag is not writable",Toast.LENGTH_LONG).show();
                    ndef.close();
                    return;
                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
                Toast.makeText(NewBag.this,"Tag is written",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Log.e("writeNdefMessage",e.getMessage());
        }
    }

    private NdefRecord createTextRecord(String content){

        try {
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = content.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte) (languageSize & 0x1F));
            payload.write(language, 0, languageSize);
            payload.write(text, 0, textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());

        } catch (UnsupportedEncodingException e) {
            Log.e("createTextRecord", e.getMessage());
        }
        return null;
    }

    private NdefMessage createNdefMessage(String content){

        NdefRecord ndefRecord = createTextRecord(content);
        NdefMessage ndefMessage= new NdefMessage(new NdefRecord[]{ndefRecord});
        return ndefMessage;
    }




}
