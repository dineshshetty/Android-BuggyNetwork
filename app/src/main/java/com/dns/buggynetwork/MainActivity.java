package com.dns.buggynetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    String request_endpoint = "https://twitter.com/Din3zh";
    String[] pinningCerts = new String[]  {
            "sha256/BRvG5szpZyF6p3BXtjMBvcFuZDYOrrUzhx2UqcYhkwE=",
            "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws="};
    TextView logsStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String hostname = getHostName(request_endpoint);
        System.out.println(hostname);
        logsStuff = (TextView) findViewById(R.id.textview_logs);


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
              // requestTypeThree.execute(request_endpoint,hostname);
                String receivedData = "";
                try {
                    receivedData = requestTypeThree.execute(request_endpoint,hostname, pinningCerts[0],pinningCerts[1]).get().toString();
                    System.out.println("receivedData = "+receivedData);
                    logsStuff.setText(receivedData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public String getHostName(String url) {
        URI uri = null;
        String hostname = "";
        try {
            uri = new URI(url);
            System.out.println("enterd getHostName");
            hostname = uri.getHost();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            return hostname;
        }

    }
}
