package com.example.a16033774.fourtruthonelieadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    TextView tvKey;
    TextView tvDuration;
    TextView tvRounds;
    EditText etKey;
    Spinner spinnerDuration;
    Spinner spinnerRounds;
    Button btnCreate;
//    Button btnSelectExisting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        tvKey = (TextView) findViewById(R.id.textViewGameKey);
        tvDuration = (TextView) findViewById(R.id.textViewDuration);
        tvRounds = (TextView) findViewById(R.id.textViewRounds);
        etKey = (EditText) findViewById(R.id.editTextGameKey);
        spinnerDuration = (Spinner) findViewById(R.id.spinnerDuration);
        spinnerRounds = (Spinner) findViewById(R.id.spinnerRounds);
        btnCreate = (Button) findViewById(R.id.buttonCreate);



        //create contact
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for step 1 start
                String url = "http://localhost/FYPProject/createGame.php?gameKey="+etKey.getText().toString()+"&duration="+spinnerDuration.getSelectedItem().toString()+"&number_of_round="+spinnerRounds.getSelectedItem().toString();

                HttpRequest request = new HttpRequest(url);



                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
//                request.addData("gameKey", etKey.getText().toString());
//                request.addData("duration", spinnerDuration.getSelectedItem().toString());
//                request.addData("number_of_round", spinnerRounds.getSelectedItem().toString());



                request.execute();
                // Code for step 1 end

               Intent intent = new Intent(MainActivity.this, SecondActivity.class);
               intent.putExtra("rounds", spinnerRounds.getSelectedItem().toString());
               intent.putExtra("duration", spinnerDuration.getSelectedItem().toString());
                intent.putExtra("gamekey", etKey.getText().toString());
                startActivity(intent);

               //find out how many rounds

            }
        });
    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_LONG).show();
                        Log.d("MainActivity",jsonObj.getString("message")); //debugging

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
            };


    }
