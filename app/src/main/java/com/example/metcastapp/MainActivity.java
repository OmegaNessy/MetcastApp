package com.example.metcastapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metcastapp.models.DataDTO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static final int CONNECTION_TIMEOUT = 10;
    private TextView mCity;
    private TextView mOutput;
    private RecyclerView mRecyclerView;
    private TextView mTemp;
    private TextView mUnderMain;
    Adapter adapter;
    final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=9a37a70a447ffa50c1c9d0f7f63775cd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mCity = findViewById(R.id.city);
        mOutput = findViewById(R.id.Output);
        mCity.setText("Минск");
        mRecyclerView = findViewById(R.id.recyclerview);
        mTemp = findViewById(R.id.mainTemp);
        mUnderMain = findViewById(R.id.underMain);


        //adapter = new Adapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL githubEndpoint = null;
                try {
                    githubEndpoint = new URL(BASE_URL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                try {
                    HttpURLConnection myConnection =
                            (HttpURLConnection) githubEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        Gson g = new Gson();
                        DataDTO data = g.fromJson(responseBodyReader,DataDTO.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mCity.setText(data.name);
                                TempConverter t = new TempConverter();
                                mUnderMain.setText(t.convertToCelsium(data.main.temp_max)+"°"+" / "+t.convertToCelsium(data.main.temp_min)+"°");
                                mTemp.setText(t.convertToCelsium(data.main.temp)+"°");
                            }
                        });
                        myConnection.disconnect();
                    } else {
                        // Error handling code goes here
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}