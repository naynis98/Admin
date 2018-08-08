package com.example.a16033774.fourtruthonelieadmin;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    Button btnGenerate, btn_round1, btn_round2, btn_round3, btn_round4, btn_round5, btn_start, btn_result;
    TextView tv_timer;

    ArrayList<User> alUser;
    ArrayAdapter<User> aaUser;

    ArrayList<Integer> row2;

    int id;
    String user;
    int firstElement;

    //timer
    private long COUNTDOWN_IN_MILLIS = 300000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMills;
    private ColorStateList textColorDefaultcd;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        aaUser = new ArrayAdapter(this, R.layout.row, alUser);


        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        btn_round1 = (Button) findViewById(R.id.btn_round1);
        btn_round2 = (Button) findViewById(R.id.btn_round2);
        btn_round3 = (Button) findViewById(R.id.btn_round3);
        btn_round4 = (Button) findViewById(R.id.btn_round4);
        btn_round5 = (Button) findViewById(R.id.btn_round5);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_result = (Button) findViewById(R.id.btn_result);
        tv_timer = (TextView) findViewById(R.id.tv_timer);

        Intent i = getIntent();
        COUNTDOWN_IN_MILLIS = new Long(i.getStringExtra("duration")) * 60 * 1000;


        //timer text colour
        textColorDefaultcd = tv_timer.getTextColors();
        timeLeftInMills = COUNTDOWN_IN_MILLIS;

        final String numRounds = i.getStringExtra("rounds");
        final String gameKey = i.getStringExtra("gamekey");


        //btnGenerate will generate pairs by random
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for step 1 start
                String url = "http://10.0.2.2/FYPProject/generatePairs.php?round=" + numRounds + "&gamekey1=" + gameKey;

                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");

                request.execute();
            }

        });


        //timer will only start when "start game" button is pressed
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //check for duration and set the timer
//                Intent intent = getIntent();
//                String dataDuration = intent.getStringExtra("duration");
//                long time = Long.parseLong(dataDuration);

                countDownTimer = new CountDownTimer(timeLeftInMills, 1000) {
                    @Override
                    public void onTick(long millisUntilFinish) {
                        timeLeftInMills = millisUntilFinish;
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                        timeLeftInMills = 0;
                        updateCountDownText();

                    }
                }.start();
            }

            //creating timer colour
            private void updateCountDownText() {
                int minutes = (int) (timeLeftInMills / 1000) / 60;
                int second = (int) (timeLeftInMills / 1000) % 60;

                String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, second);

                tv_timer.setText(timeFormatted);

                if (timeLeftInMills < 60000) {
                    tv_timer.setTextColor(Color.RED);
                } else {
                    tv_timer.setTextColor(textColorDefaultcd);
                }

            }

        });

        //check for the number of rounds and disable the rounds button accordingly
        Intent intent = getIntent();
        String dataRounds = intent.getStringExtra("rounds");
        if (dataRounds.equals("1")) {
            btn_round2.setEnabled(false);
            btn_round3.setEnabled(false);
            btn_round4.setEnabled(false);
            btn_round5.setEnabled(false);
        } else if (dataRounds.equals("2")) {
            btn_round3.setEnabled(false);
            btn_round4.setEnabled(false);
            btn_round5.setEnabled(false);
        } else if (dataRounds.equals("3")) {
            btn_round4.setEnabled(false);
            btn_round5.setEnabled(false);
        } else if (dataRounds.equals("4")) {
            btn_round5.setEnabled(false);
        }

        btn_round1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, roundOne.class);
                startActivity(intent);
            }
        });


    } //end

    // http response for generate button
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObject = new JSONObject(response);
                        String message = jsonObject.getString("message");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
}
