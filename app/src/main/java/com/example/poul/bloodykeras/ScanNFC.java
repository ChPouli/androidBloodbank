package com.example.poul.bloodykeras;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;


public class ScanNFC extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    TextView txtTagdate;
    TextView txtTagiddonor;
    TextView txtTagvolume;
    TextView txtTaganti;
    TextView txtTagkind;
    TextView txtTagbloodtype;
    TextView txtTagrh;
    TextView txtTagavailable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_nfc);
        txtTagdate=(TextView)findViewById(R.id.txtTagdate);
        txtTagiddonor=(TextView)findViewById(R.id.txtTagiddonor);
        txtTagvolume=(TextView)findViewById(R.id.txtTagvolume);
        txtTaganti=(TextView)findViewById(R.id.txtTaganti);
        txtTagkind=(TextView)findViewById(R.id.txtTagkind);
        txtTagbloodtype=(TextView)findViewById(R.id.txtTagbloodtype);
        txtTagrh=(TextView)findViewById(R.id.txtTagrh);
        txtTagavailable=(TextView)findViewById(R.id.txtTagavailable);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Approach an NFC tag to your device ");
        alertDialogBuilder.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nfcAdapter= NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter !=null && nfcAdapter.isEnabled()){
            // Toast.makeText(NewBag.this,"nfc available ",Toast.LENGTH_LONG).show();

        }
        else {
            finish();

        }

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

    @Override
    protected void onNewIntent(Intent intent) {


        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            // Toast.makeText(this, "NfcIntent!", Toast.LENGTH_SHORT).show();

            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if(parcelables != null && parcelables.length > 0)
            {
                readTextFromMessage((NdefMessage) parcelables[0]);
            }else{
                Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
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
    //nf enable disable functions
    private void enableForegroundDispatchSystem(){
        Intent intent= new Intent( ScanNFC.this,ScanNFC.class);
        intent.addFlags(intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);
        IntentFilter[] intentfilter= new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentfilter, null);

    }
    private void disableForegroundDispatchSystem(){
        nfcAdapter.disableForegroundDispatch(this);
    }

    //region Read nfc functions
    private void readTextFromMessage(NdefMessage ndefMessage) {

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if(ndefRecords != null && ndefRecords.length>0){

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            String[] parts = tagContent.split(" ");


               // String s1="<strong></strong>";
            // a SpannableStringBuilder containing text to display
            SpannableStringBuilder sb = new SpannableStringBuilder("Produced date:  " + parts[0] );
            SpannableStringBuilder sb2 = new SpannableStringBuilder("Donor's id:  " +parts[1] );

            // create a bold StyleSpan to be used on the SpannableStringBuilder
            //StyleSpan b = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold

            // set only the name part of the SpannableStringBuilder to be bold
           // sb.setSpan(b, 16, 16 + parts[0].length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
           // sb2.setSpan(b, 13, 13 + parts[1].length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            txtTagdate.setText(sb); // set the TextView to be the SpannableStringBuilder
            txtTagiddonor.setText(sb2);
            txtTagvolume.setText("Blood volume:"+" "+parts[2]);
            txtTaganti.setText("Bag Anticoagulant:" + " " + parts[3]);

            if (parts.length==4 ) {
                txtTagkind.setText("Blood product:"+ " " + "pending results");
                 txtTagbloodtype.setText("Bloodtype:"+ " "+ "pending results");
                 txtTagrh.setText("Rh:"+ " " + "pending results");

            }else {

            txtTagkind.setText("Blood product:"+ " " + parts[4]+" "+parts[5]);
           txtTagbloodtype.setText("Bloodtype:"+ " "+ parts[6]);
           txtTagrh.setText("Rh:"+ " " +parts[7]);
            }

        }else
        {
            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
        }

    }
    public String getTextFromNdefRecord(NdefRecord ndefRecord)
    {
        String tagContent = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1,
                    payload.length - languageSize - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("getTextFromNdefRecord", e.getMessage(), e);
        }
        return tagContent;
    }

    //endregion

}
