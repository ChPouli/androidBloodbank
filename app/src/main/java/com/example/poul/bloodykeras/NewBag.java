package com.example.poul.bloodykeras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NewBag extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner typeSp, antipiktikoSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bag);
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
        String item = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



}
