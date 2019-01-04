package com.example.victor.myapplication.query.musicDemons;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APITask extends AsyncTask<Void, Void, MusicDemonsSongWithLyricsResponse> {
    private static final String baseUrl = "https://musicdemons.com/api/v1/song/organic-search/";
    private MusicDemonsQuery mdq;

    public APITask(MusicDemonsQuery mdq) {
        this.mdq = mdq;
    }
    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected MusicDemonsSongWithLyricsResponse doInBackground(Void... voids) {
        System.out.println("Do In Background");
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append(mdq.getTrackName()).append(" ").append(mdq.getArtist());
        String urlString = sb.toString().replaceAll(" ", "%20");
        System.out.println(urlString);
        try {
            URL url = new URL(sb.toString());
            // default settings are ok.
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpCon.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            System.out.println("output");
            System.out.println(new BufferedReader(reader).readLine());
//            MusixmatchResponse response = new GsonBuilder()
//                    .registerTypeAdapter(MusixmatchResponse.class, new MusixmatchResponseDeserializer()).create()
//                    .fromJson(reader, MusixmatchResponse.class);
            reader.close();
            inputStream.close();
            return null; // TODO : change back to response
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(MusicDemonsSongWithLyricsResponse mdswlr) {
        System.out.println("On Post Execute");
        mdq.done(mdswlr);
    }
}
