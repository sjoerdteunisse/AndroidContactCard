package com.axr.sjoerd.contactcard.ApplicationLogic;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class RetrieveData extends AsyncTask<Void, Void, String> {
    Exception exception;

    @Override
    protected String doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        URL url;
        try {
            url = new URL("https://randomuser.me/api/");
            urlConnection.setRequestMethod("GET"); //Your method here
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0)
                return null;

            return buffer.toString();
        } catch (IOException e) {
            Log.e("", "IO Exception", e);
            exception = e;
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    exception = e;
                    Log.e("", "Error closing stream", e);
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String response) {
        if(response != null) {
            JSONObject json = null;
            try {
                JSONObject jsonResponse = json.getJSONObject("response");
                String team = jsonResponse.getString("Team");
                json = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}