package com.example.poul.bloodykeras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.User;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class PatientScreen extends AppCompatActivity {

   private EditText lname ;

    private  EditText fname;
   private EditText fathername;

   private EditText byear ;
   private EditText phone;

   private EditText address;
   private EditText AT;

   private EditText clinic;
   private EditText diagnosis;

   private EditText  bloodtype;
   private EditText Rh ;

    private  EditText fenotypos;
   private EditText antisomata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);
        lname = (EditText)findViewById(R.id.LastNameEditText);

        fname = (EditText)findViewById(R.id.FirstNameEditText);
        fathername = (EditText)findViewById(R.id.FatherNameEditText);

        byear = (EditText)findViewById(R.id.BirthYearEditText);
        phone = (EditText)findViewById(R.id.TelephoneEditText);

        address = (EditText)findViewById(R.id.AddressEditText);
        AT = (EditText)findViewById(R.id.IdEditText);

        clinic = (EditText)findViewById(R.id.clinicEditText);
        diagnosis = (EditText)findViewById(R.id.DiagnosisEditText);

        bloodtype = (EditText)findViewById(R.id.BloodTypePEditText);
        Rh = (EditText)findViewById(R.id.RhPEditText);

        fenotypos = (EditText)findViewById(R.id.FenotyposPEditText);
        antisomata = (EditText)findViewById(R.id.AntisomataPEditText);
    }

    public void addPatientBtn(View view){
       
        APIServiceAdapter.getInstance().addPatient(lname.getText().toString(),fname.getText().toString(),
                fathername.getText().toString(),byear.getInputType(),phone.getInputType(),
                address.getText().toString(),AT.getText().toString(),clinic.getText().toString(),
                diagnosis.getText().toString(),bloodtype.getText().toString(),Rh.getText().toString(),
                fenotypos.getText().toString(),antisomata.getText().toString()
                ).
                subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("skata", e.getMessage());
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        Toast.makeText(PatientScreen.this,"Successfully insert",Toast.LENGTH_LONG).show();


                    }
                });
    }


}
