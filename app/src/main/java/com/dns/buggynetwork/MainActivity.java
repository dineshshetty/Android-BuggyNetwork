package com.dns.buggynetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String request_endpoint = "https://twitter.com/Din3zh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button httpURLConnectionGetButton = (Button) findViewById(R.id.button_http_url_connection_get);
        httpURLConnectionGetButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                HttpURLConnectionGetRequest requestTypeOne = new HttpURLConnectionGetRequest();
                requestTypeOne.execute(request_endpoint);
            }
        });

        Button okHttpClientGetButton = (Button) findViewById(R.id.button_ok_http_get);
        okHttpClientGetButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                OkHttpClientGetRequest requestTypeTwo = new OkHttpClientGetRequest();
                requestTypeTwo.execute(request_endpoint);
            }
        });

        Button okHttpClientPinnedGetButton = (Button) findViewById(R.id.button_pinned_ok_http_get);
        okHttpClientPinnedGetButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                OkHttpClientPinnedGetRequest requestTypeThree = new OkHttpClientPinnedGetRequest();
                requestTypeThree.execute(request_endpoint);
            }
        });

    }
}
