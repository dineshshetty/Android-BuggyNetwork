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

            String hostname = parameters[1]; //eg: twitter.com
            String pinnedCert1 = parameters[2]; //eg: "sha256/BRvG5szpZyF6p3BXtjMBvcFuZDYOrrUzhx2UqcYhkwE="
            String pinnedCert2 = parameters[3]; //eg: "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws="
            System.out.println("YES OkHttpClientPinnedGetRequest ");

            CertificatePinner pinnedCertificatesList = new CertificatePinner.Builder()
                    .add(hostname, pinnedCert1)
                    .add(hostname, pinnedCert2)
                    .build();

            OkHttpClient httpConn = new OkHttpClient.Builder()
                    .certificatePinner(pinnedCertificatesList)
                    .build();


            Request httpRequest = new Request.Builder().url(siteURL).build();
            httpRequest.cacheControl().noCache();
            httpRequest.cacheControl().noStore();

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
