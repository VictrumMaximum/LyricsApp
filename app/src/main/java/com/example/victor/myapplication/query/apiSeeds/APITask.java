package com.example.victor.myapplication.query.apiSeeds;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APITask extends AsyncTask<Void, Void, ApiSeedsResponse> {
    private static final String baseUrl = "https://orion.apiseeds.com/api/music/lyric/";
    private ApiSeedsQuery asq;
    private static final String testResponse = "{\"result\":{\"artist\":{\"name\":\"LMFAO\"},\"copyright\":{\"artist\":\"Copyright LMFAO\",\"notice\":\"Sexy and I Know It lyrics are property and copyright of their owners. Commercial use is not allowed.\",\"text\":\"All lyrics provided for educational purposes and personal use only.\"},\"probability\":\"100\",\"similarity\":\"1\",\"track\":{\"lang\":{\"code\":\"en\",\"name\":\"English\"},\"name\":\"Sexy and I Know It\",\"text\":\"When I walk on by\\nGirls be looking like damn he fly\\nI pay to the beat\\nWalking on the street\\nWith in my new lafreak, yeah\\nThis is how I roll\\nAnimal print\\nPants out control,\\nIt\\u0027s RedFOO with the big afro\\nAnd like Bruce Leroy I got the glow\\n\\nGirl look at that body\\nGirl look at that body\\nGirl look at that body\\nI work out\\nGirl look at that body\\nGirl look at that body\\nGirl look at that body\\nI work out\\n\\nWhen I walk in the spot\\nThis is what I see\\nEverybody stops\\nAnd they staring at me\\nI got passion in my pants\\nAnd I ain\\u0027t afraid to show it\\nShow it, show it, show it\\n\\nI\\u0027m sexy and I know it\\nI\\u0027m sexy and I know it\\n\\nYeah, when I\\u0027m at the moss\\nPurity just can\\u0027t fight them up\\nWhen I\\u0027m at the beach\\nI\\u0027m in a speedo trying to tan my cheeks\\nThis is how I roll\\nCome on ladies it\\u0027s time to go\\n\\nWe hit it to the bar\\nBaby don\\u0027t be nervous\\nNo shoes, no shirt\\nAnd I still get serviced\\n\\nGirl look at that body\\nGirl look at that body\\nGirl look at that body\\nI work out\\nGirl look at that body\\nGirl look at that body\\nGirl look at that body\\nI work out\\n\\nWhen I walk in the spot\\nThis is what I see\\nEverybody stops\\nAnd they staring at me\\nI got passion in my pants\\nAnd I ain\\u0027t afraid to show it\\nShow it, show it, show it\\n\\nI\\u0027m sexy and I know it\\nI\\u0027m sexy and I know it\\n\\nCheck it out, check it out\\n\\nWiggle, wiggle, wiggle, wiggle, yeah...\\n\\nDo the wiggle man\\nI do the wiggle man\\nYeah\\n\\nI\\u0027m sexy and I know it\\n\\nGirl look at that body\\nGirl look at that body\\nGirl look at that body\\nI work out\\nGirl look at that body\\nGirl look at that body\\nGirl look at that body\\nI work out\\n\\nI\\u0027m sexy and I know it\"}}}";

    public APITask(ApiSeedsQuery asq) {
        this.asq = asq;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected ApiSeedsResponse doInBackground(Void... voids) {
        System.out.println("Do In Background");
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append(asq.getArtist()).append("/").append(asq.getTrack()).append("?")
                .append("apikey=").append(asq.getApiKey());
        String urlString = sb.toString().replaceAll(" ", "%20");
        System.out.println(urlString);
        try {
            URL url = new URL(urlString);
            // default settings are ok.
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpCon.getInputStream();
            InputStream inputStream = new ByteArrayInputStream(testResponse.getBytes("UTF-8"));
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            ApiSeedsResponse response = new Gson().fromJson(reader, ApiSeedsResponse.class);
            reader.close();
            inputStream.close();
            return response; // TODO : change back to response
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ApiSeedsResponse asr) {
        System.out.println("On Post Execute");
        asq.done(asr);
    }
}
