package com.dns.buggynetwork;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

class HttpURLConnectionGetRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... parameters) {
        try {
            URL siteURL = new URL(parameters[0]);
            System.out.println("YES HttpURLConnectionGetRequest ");
            HttpURLConnection httpConn = (HttpURLConnection) siteURL.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            InputStream inputStream = httpConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            System.out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
        return "SUCCESS";
    }


    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        System.out.println("Request Status: " + result);
    }
}
