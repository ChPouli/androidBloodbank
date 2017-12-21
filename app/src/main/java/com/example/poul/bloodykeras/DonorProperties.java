package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import rx.Subscriber;

public class DonorProperties extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText lname ;

    private  EditText fname;
    private EditText fathername;

    private EditText byear ;
    private EditText phone;

    private EditText address;
    private EditText AT;

    private EditText occupation;
    private EditText birthplace;

    private EditText  bloodtype;
    private EditText Rh ;
    private Spinner btypeSp, rhSp;
    private String bloodTypeString,RhString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_properties);

        //enable back arrow toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lname = (EditText)findViewById(R.id.LastNameEditText);

        fname = (EditText)findViewById(R.id.FirstNameEditText);
        fathername = (EditText)findViewById(R.id.FatherNameEditText);

        byear = (EditText)findViewById(R.id.BirthYearEditText);
        phone = (EditText)findViewById(R.id.TelephoneEditText);

        address = (EditText)findViewById(R.id.AddressEditText);
        AT = (EditText)findViewById(R.id.IdEditText);

        occupation = (EditText)findViewById(R.id.JobEditText);
        birthplace = (EditText)findViewById(R.id.BirthPlaceEditText);

        //spinner add
        addItemsOnSpinners();
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

    //region Spinner function

    public void addItemsOnSpinners(){
        btypeSp = (Spinner) findViewById(R.id.BtypeSpdonor);
        rhSp = (Spinner) findViewById(R.id.RhSpdonor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterBtype = ArrayAdapter.createFromResource(this,R.array.Blood_Type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRh = ArrayAdapter.createFromResource(this,R.array.Rh, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterBtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btypeSp.setAdapter(adapterBtype);
        btypeSp.setOnItemSelectedListener(this);
        rhSp.setAdapter(adapterRh);
        rhSp.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        bloodTypeString = btypeSp.getSelectedItem().toString();
        //String item = parent.getItemAtPosition(pos).toString();
        RhString=rhSp.getSelectedItem().toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



    //endregion

    public void addDonorBtn (View view){
        APIServiceAdapter.getInstance().addDonor(lname.getText().toString(),fname.getText().toString(),
                                                fathername.getText().toString(),byear.getText().toString(),phone.getText().toString(),
                                                address.getText().toString(), AT.getText().toString(),occupation.getText().toString()
                                                ,birthplace.getText().toString(),bloodTypeString,
                                                    RhString)
                .subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("donorgamietai", e.getMessage());
            }

            @Override
            public void onNext(Void aVoid) {
                Toast.makeText(DonorProperties.this, "Successfully inserted Donor",Toast.LENGTH_LONG).show();
                    finish();
            }
        });

    }
}
