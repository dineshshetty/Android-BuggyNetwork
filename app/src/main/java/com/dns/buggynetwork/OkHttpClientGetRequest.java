package com.dns.buggynetwork;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class OkHttpClientGetRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... parameters) {
        try {
            URL siteURL = new URL(parameters[0]);
            System.out.println("YES OkHttpClientGetRequest ");


            OkHttpClient httpConn = new OkHttpClient();

            Request httpRequest = new Request.Builder().url(siteURL).build();

            Response httpResponse = httpConn.newCall(httpRequest).execute();

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
