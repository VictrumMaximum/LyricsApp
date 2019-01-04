package com.example.victor.myapplication;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LyricsFetcher extends AsyncTask<String, Character, MusixmatchResponse> {

    private WeakReference<MainActivity> activity;
    private Exception error;

    private static final String baseURL = "https://api.musixmatch.com/ws/1.1/track.search?";

    LyricsFetcher(WeakReference<MainActivity> activity) {
        this.activity = activity;
        this.error = null;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected MusixmatchResponse doInBackground(String... strings) {
        System.out.println("Do In Background");
        String apiKey = strings[0];
        String track = "Sultans Of Swing";//strings[1];
        String artist = "Dire Straits";//strings[2];
        String album = "";//strings[3];

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(baseURL)
                    .append("apikey=").append(apiKey)
                    .append("&q_track=Sultans Of Swing")
                    .append("&q_artist=Dire Straits");
            URL url = new URL(sb.toString());
            // default settings are ok.
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpCon.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            Gson gson = new Gson();
            MusixmatchResponse response =  gson.fromJson(reader, MusixmatchResponse.class);
            reader.close();
            inputStream.close();
            return response;
        } catch (Exception e) {
            this.error = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(MusixmatchResponse mmr) {
        System.out.println("On Post Execute");
        if (this.activity.get() != null) { // check if activity is still in memory
            if (this.error != null) {
                this.activity.get().reportError(error);
            } else {
                this.activity.get().setText(mmr);
            }
        }
    }
}
