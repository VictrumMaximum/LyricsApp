package com.example.victor.myapplication.query.musixmatch;

import com.example.victor.myapplication.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TrackSearchQuery extends MusixmatchQuery {
    private static final String TYPE = "track.search";
    public TrackSearchQuery(MainActivity activity) {
        super(activity);
    }
    public String getType() {
        return TYPE;
    }
    public void success(MusixmatchResponse mmr) {
        ((MainActivity)activity).resetTask();
        if (mmr == null) {
            System.out.println("TASK RETURNED NULL");
            return;
        }
        if (mmr.header.status_code != 200) {
            String errorMsg = new Gson().fromJson(mmr.body, String.class);
            System.out.println("Error in response: code " + mmr.header.status_code + ", " + errorMsg);
            return;
        }
        TrackSearchBody body = new GsonBuilder()
                .registerTypeAdapter(TrackSearchBody.class, new TrackSearchBodyDeserializer()).create()
                .fromJson(mmr.body, TrackSearchBody.class);
        System.out.println("track_list length: " + body.track_list.length);
    }
}
