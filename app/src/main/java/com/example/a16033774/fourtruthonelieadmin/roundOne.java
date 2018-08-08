package com.example.a16033774.fourtruthonelieadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class roundOne extends AppCompatActivity {
    ListView user11;
    ListView user12;

    ArrayList<String> arrayList1 = new ArrayList<String>();
    ArrayAdapter<String> aaUser1;

    ArrayList<String> arrayList2 = new ArrayList<String>();
    ArrayAdapter<String> aaUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_one);
        user11 = (ListView) findViewById(R.id.user11);
        user12 = (ListView) findViewById(R.id.user12);

        //List<String> arrayList = new ArrayList<String>();
        aaUser1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList1);
        user11.setAdapter(aaUser1);
        aaUser2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList2);
        user12.setAdapter(aaUser2);


        // getting from pairing table
        String url = "http://10.0.2.2/FYPProject/getPairs.php";

        HttpRequest request = new HttpRequest(url);

        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");

        request.execute();



    }


    // user1
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            int pairingid = jsonObject.getInt("pairingid");
                            int gamekey1 = jsonObject.getInt("gamekey1");
                            int user1 = jsonObject.getInt("user1");
                            int user2 = jsonObject.getInt("user2");
                            int round = jsonObject.getInt("round");
                            //get from user table
                            String userName1 = jsonObject.getString("user");


                            User userPairing = new User(pairingid, gamekey1, user1, user2, round);
                            Log.d("SecondActivity", "" + userPairing.toString());


                            // user2
                            String url2 = "http://10.0.2.2/FYPProject/getNameById.php?id=" + user2;

                            HttpRequest request2 = new HttpRequest(url2);

                            request2.setOnHttpResponseListener(user2HttpResponseListener);
                            request2.setMethod("GET");

                            request2.execute();


                            //displaying pairs for user1
                            arrayList1.add(userName1);
                        }
                        aaUser1.notifyDataSetChanged();
//                        aaUser2.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };


    //user2
    private HttpRequest.OnHttpResponseListener user2HttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        //display pair for user2
                        String userName2 = jsonObject.getString("user");
                        arrayList2.add(userName2);
                        aaUser2.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            };
}



