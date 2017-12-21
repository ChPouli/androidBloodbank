package com.example.poul.bloodykeras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.Patient;
import com.example.poul.bloodykeras.Model.User;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;

import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         username = (EditText)findViewById(R.id.editText);

         password = (EditText)findViewById(R.id.textPass);

    }



    public void loginScreen(View view){
        Log.e("skatoules", "lla");
        APIServiceAdapter.getInstance().authenticate(
                username.getText().toString(),
                password.getText().toString())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("skata", e.getMessage());
                    }

                    @Override
                    public void onNext(List<User> users) {

                        if (users.size() > 0) {
//Integer.toString(users.size())
                           // Toast.makeText(MainActivity.this, users.get(0).getFname() + " "
                             //       + users.get(0).getId(), Toast.LENGTH_SHORT).show();

                            //gia na ka8arisei edit text wste otan gyrnw me finish na nai ka8ara
                            username.getText().clear();
                            password.getText().clear();
                            Intent intent = new Intent(MainActivity.this, NDchoices.class);
                            intent.putExtra("userid",users.get(0).getId());

                            startActivity(intent);}

                        else

                            Toast.makeText(MainActivity.this, "Wrong username or password!\nTry again.", Toast.LENGTH_SHORT).show();
                            username.getText().clear();
                             password.getText().clear();

                    }
                });

        //Intent intent = new Intent(this, keratiatikachoices.class);
        //startActivity(intent);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "You must login first",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
