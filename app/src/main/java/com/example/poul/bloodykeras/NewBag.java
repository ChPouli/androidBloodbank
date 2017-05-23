package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import rx.Subscriber;

public class NewBag extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner typeSp, antipiktikoSp;
    private int iduser;
    private int iddonor;
    private int idsession;
    private  String  bagtype ,anti;
    private EditText volume;
    private  EditText nfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bag);
        Intent getextras=getIntent();
        iduser=getextras.getIntExtra("userid",0);
        iddonor=getextras.getIntExtra("donorid",0);
        idsession=getextras.getIntExtra("sessionid",0);
        volume=(EditText)findViewById(R.id.volumenewbagEditText);
        nfc=(EditText)findViewById(R.id.TagrfidnewbagEditText);
        //addItemsOnBagSpinner();
        addItemsOnSpinners();
    }

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

public  void addNewBag(View view){

    APIServiceAdapter.getInstance().addNewBag(bagtype,anti,volume.getText().toString(),idsession,nfc.getText().toString()).subscribe(new Subscriber<Void>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Void aVoid) {
                    Toast.makeText(NewBag.this, "kaleeeee" + idsession ,Toast.LENGTH_LONG).show();
        }
    });


}

}
