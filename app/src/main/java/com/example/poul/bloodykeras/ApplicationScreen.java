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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Subscriber;

public class ApplicationScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener{


    String formattedDate;
    Button btn;
    private int iduser;
    private int idpatient;


    private EditText clinic;
    private EditText quantity;


    private Spinner btypeSp, rhSp,kindSp , prioritySp;
    private String bloodTypeString,RhString,kindString,priorityString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_screen);
        Intent getextras=getIntent();
        iduser=getextras.getIntExtra("userid",0);
        idpatient=getextras.getIntExtra("patientid",0);

        clinic= (EditText)findViewById(R.id.ClinicAppEditText);
        quantity=(EditText)findViewById(R.id.QuantityEditText);



        //spinner add
        addItemsOnSpinners();


        //enable back arrow toolbar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);








    }

    //region Spinner handling

    public void addItemsOnSpinners(){
        btypeSp = (Spinner) findViewById(R.id.BtypeSpApp);
        rhSp = (Spinner) findViewById(R.id.RhSpApp);
        kindSp=(Spinner) findViewById(R.id.kindSpApp);
        prioritySp=(Spinner) findViewById(R.id.PrioritySpApp);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterBtype = ArrayAdapter.createFromResource(this,R.array.Blood_Type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRh = ArrayAdapter.createFromResource(this,R.array.Rh, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterProduct = ArrayAdapter.createFromResource(this,R.array.Blood_Product, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(this,R.array.Priority_App, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterBtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btypeSp.setAdapter(adapterBtype);
        btypeSp.setOnItemSelectedListener(this);
        rhSp.setAdapter(adapterRh);
        rhSp.setOnItemSelectedListener(this);
        kindSp.setAdapter(adapterProduct);
        kindSp.setOnItemSelectedListener(this);
        prioritySp.setAdapter(adapterPriority);
        prioritySp.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        bloodTypeString = btypeSp.getSelectedItem().toString();
        //String item = parent.getItemAtPosition(pos).toString();
        RhString=rhSp.getSelectedItem().toString();

        kindString=kindSp.getSelectedItem().toString();
        priorityString=prioritySp.getSelectedItem().toString();


    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



    //endregion

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
                clinic.getText().toString(),kindString ,priorityString,
                formattedDate , bloodTypeString,RhString).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {
                Toast.makeText(ApplicationScreen.this, "Request successfully inserted", Toast.LENGTH_LONG).show();
                finish();

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

            DatePickerDialog datePickerDialog =  new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            return datePickerDialog;


            // Create a new instance of DatePickerDialog and return it
           // return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }


    }
}