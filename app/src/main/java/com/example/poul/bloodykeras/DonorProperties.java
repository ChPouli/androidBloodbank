package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import rx.Subscriber;

public class DonorProperties extends AppCompatActivity {

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

        bloodtype = (EditText)findViewById(R.id.bloodtypeEditText);
        Rh = (EditText)findViewById(R.id.RhEditText);

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

    public void addDonorBtn (View view){
        APIServiceAdapter.getInstance().addDonor(lname.getText().toString(),fname.getText().toString(),
                                                fathername.getText().toString(),byear.getInputType(),phone.getInputType(),
                                                address.getText().toString(), AT.getText().toString(),occupation.getText().toString()
                                                ,birthplace.getText().toString(),bloodtype.getText().toString(),
                                                    Rh.getText().toString())
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

            }
        });

    }
}
