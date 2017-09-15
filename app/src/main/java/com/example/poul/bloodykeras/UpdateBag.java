package com.example.poul.bloodykeras;

import android.app.Dialog;
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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import rx.Subscriber;

public class UpdateBag extends AppCompatActivity {
    private EditText kind;
    private EditText bloodtype;
    private EditText rh;
    private EditText checked;

    private EditText available;
    NfcAdapter nfcAdapter;
    byte[] uid;
    String oldNFCdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bag);

        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kind = (EditText) findViewById(R.id.kindupdatebagEditText);
        bloodtype = (EditText) findViewById(R.id.bloodtypebagEditText);
        rh = (EditText) findViewById(R.id.RhupdatebagEditText);
        checked = (EditText) findViewById(R.id.checkedupdatebagEditText);

        available = (EditText) findViewById(R.id.availableupdatebagEditText);

        //nfc adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //nfc enable disable functions
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            // Toast.makeText(NewBag.this,"nfc available ",Toast.LENGTH_LONG).show();

        } else {
            finish();

        }



    }

    //me to poy piasei intent apo nfc diabazei uid
    protected void onNewIntent(Intent intent) {


        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //pairnw to byte array me to uid
            uid = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (parcelables != null && parcelables.length > 0) {
                readTextFromMessage((NdefMessage) parcelables[0]);
            } else {
                Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
            }


            String rec = oldNFCdata + " " + kind.getText().toString() + " " + bloodtype.getText().toString()
                    + " " + rh.getText().toString() + " " + available.getText().toString();
            // Toast.makeText(NewBag.this,rec,Toast.LENGTH_LONG).show();
            NdefMessage ndefMessage = createNdefMessage(rec);

            writeNdefMessage(tag, ndefMessage);
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

    //region write NFC functions
    private void writeNdefMessage(Tag tag, NdefMessage ndefMessage) {

        try {
            if (tag == null) {
                Toast.makeText(UpdateBag.this, "Tag object cannot be null", Toast.LENGTH_LONG).show();
                return;
            }

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                //format tag with ndef format and write message
                formatTag(tag, ndefMessage);
            } else {
                ndef.connect();
                if (!ndef.isWritable()) {
                    Toast.makeText(UpdateBag.this, "Tag is not writable", Toast.LENGTH_LONG).show();
                    ndef.close();
                    return;
                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
                Toast.makeText(UpdateBag.this, "Tag is written", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Log.e("writeNdefMessage", e.getMessage());
        }
    }

    private NdefMessage createNdefMessage(String content) {

        NdefRecord ndefRecord = createTextRecord(content);
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ndefRecord});
        return ndefMessage;
    }

    private void formatTag(Tag tag, NdefMessage ndefMessage) {

        try {
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if (ndefFormatable == null) {
                Toast.makeText(UpdateBag.this, "Tag is not Ndef Formatable", Toast.LENGTH_LONG).show();
                return;
            }

            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();
            // Toast.makeText(NewBag.this,"Tag written",Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("Format tag", e.getMessage());
        }

    }

    private NdefRecord createTextRecord(String content) {

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
//endregion

    //region nf enable disable functions
    private void enableForegroundDispatchSystem() {
        Intent intent = new Intent(UpdateBag.this, UpdateBag.class);
        intent.addFlags(intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentfilter = new IntentFilter[]{};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentfilter, null);

    }

    private void disableForegroundDispatchSystem() {
        nfcAdapter.disableForegroundDispatch(this);
    }
//endregion

    //region byte array to hex string converter
    public static String byteArrayToHexString(final byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
//endregion

    //region Read nfc functions
    private void readTextFromMessage(NdefMessage ndefMessage) {

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if (ndefRecords != null && ndefRecords.length > 0) {

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            oldNFCdata = tagContent;

        } else {
            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
        }

    }

    public String getTextFromNdefRecord(NdefRecord ndefRecord) {
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
        UpdateBag.this.finish();
    }
    //endregion


    public void updateBagBtn(View view) {


        APIServiceAdapter.getInstance().updateBag(kind.getText().toString(), bloodtype.getText().toString(), rh.getText().toString()
                , checked.getText().toString(), byteArrayToHexString(uid),
                available.getText().toString())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("updateBaggamietai", e.getMessage());
                    }

                    @Override
                    public void onNext(Void aVoid) {

                        Toast.makeText(UpdateBag.this, "Bag successfully updated", Toast.LENGTH_LONG).show();

                    }
                });
    }
/*
//fragment gia nfc attach
    public static class NfcAttachFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.dialog_attach_nfc, container, false);
        }
    }
    */


}