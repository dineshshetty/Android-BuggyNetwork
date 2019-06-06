package com.dns.buggynetwork;

import android.os.AsyncTask;

import java.net.URL;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



class OkHttpClientPinnedGetRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... parameters) {
        try {
            URL siteURL = new URL(parameters[0]);
            System.out.println("YES OkHttpClientPinnedGetRequest ");

            /*
            sh certcheck.sh www.twitter.com
            /C=US/ST=California/L=San Francisco/O=Twitter, Inc./OU=atla/CN=twitter.com
            BRvG5szpZyF6p3BXtjMBvcFuZDYOrrUzhx2UqcYhkwE=
            /C=US/O=DigiCert Inc/OU=www.digicert.com/CN=DigiCert SHA2 High Assurance Server CA
            k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=
             */

            CertificatePinner pinnedCertificatesList = new CertificatePinner.Builder()
                    .add("www.twitter.com", "sha256/BRvG5szpZyF6p3BXtjMBvcFuZDYOrrUzhx2UqcYhkwE=")
                    .add("www.twitter.com", "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=")
                    .build();

            OkHttpClient httpConn = new OkHttpClient.Builder()
                    .certificatePinner(pinnedCertificatesList)
                    .build();

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
