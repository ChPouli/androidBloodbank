package com.example.poul.bloodykeras;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Subscriber;

public class ApplicationScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    String formattedDate;
    Button btn;
    private int iduser;
    private int idpatient;


    private EditText clinic;
    private EditText quantity;
    private EditText priority;
    private EditText paragogo;
    private EditText abo;
    private EditText rh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_screen);
        Intent getextras=getIntent();
        iduser=getextras.getIntExtra("userid",0);
        idpatient=getextras.getIntExtra("patientid",0);

        clinic= (EditText)findViewById(R.id.ClinicAppEditText);
        quantity=(EditText)findViewById(R.id.QuantityEditText);
        priority=(EditText)findViewById(R.id.PriorityEditText);
        paragogo=(EditText)findViewById(R.id.BloodProductEditText);
        abo=(EditText)findViewById(R.id.appABOEditText);
        rh=(EditText)findViewById(R.id.appRhEditText);


        //enable back arrow toolbar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);








    }

//o listener tou date
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       formattedDate = sdf.format(c.getTime());
        btn = (Button)findViewById(R.id.dateBtn);
        btn.setText(formattedDate);

        Toast.makeText(ApplicationScreen.this,formattedDate,Toast.LENGTH_LONG).show();

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


    //show to fragment gia to transfusion date
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public  void addNewApplication(View view){

        APIServiceAdapter.getInstance().addNewApplication(idpatient,quantity.getText().toString(),
                clinic.getText().toString(),paragogo.getText().toString() ,priority.getText().toString(),
                formattedDate , abo.getText().toString(),rh.getText().toString()).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {
                Toast.makeText(ApplicationScreen.this,"Request successfully inserted",Toast.LENGTH_LONG).show();
               // Toast.makeText(ApplicationScreen.this,"wx manoyla moy" + quantity.getText().toString(),Toast.LENGTH_LONG).show();
                // Toast.makeText(NewBag.this, "kaleeeee" + idsession, Toast.LENGTH_LONG).show();
            }
        });


    }


    //To fragment for date picker
    public static class DatePickerFragment extends DialogFragment{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }


    }
}