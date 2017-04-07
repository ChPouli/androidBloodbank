package com.example.poul.bloodykeras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import rx.Subscriber;

public class UpdateBag extends AppCompatActivity {
    private EditText kind;
    private EditText bloodtype;
    private EditText rh;
    private EditText checked;
    private EditText tagrfid;
    private EditText available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bag);

        kind=(EditText)findViewById(R.id.kindupdatebagEditText);
        bloodtype=(EditText)findViewById(R.id.bloodtypebagEditText);
        rh=(EditText)findViewById(R.id.RhupdatebagEditText);
        checked=(EditText)findViewById(R.id.checkedupdatebagEditText);
        tagrfid=(EditText)findViewById(R.id.TagrfidupdatebagEditText);
        available=(EditText)findViewById(R.id.availableupdatebagEditText);

    }


    public void updateBagBtn(View view){


        APIServiceAdapter.getInstance().updateBag(kind.getText().toString(),bloodtype.getText().toString(),rh.getText().toString()
                                                 ,checked.getText().toString(),tagrfid.getText().toString(),
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

                Toast.makeText(UpdateBag.this,"as elpisoyme",Toast.LENGTH_LONG).show();

            }
        });
    }

}
