package com.example.victor.myapplication.query.musicDemons;

import android.content.Intent;

import com.example.victor.myapplication.DisplayLyrics;
import com.example.victor.myapplication.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MatcherLyricsQuery extends MusixmatchQuery {
    private static final String TYPE = "matcher.lyrics.get";

    public MatcherLyricsQuery(MainActivity activity) {
        super(activity);
    }

    @Override
    String getType() {
        return TYPE;
    }

    @Override
    void success(MusixmatchResponse mmr) {
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
        MatcherLyricsBody body = new GsonBuilder()
                .registerTypeAdapter(MatcherLyricsBody.class, new MatcherLyricsBodyDeserializer()).create()
                .fromJson(mmr.body, MatcherLyricsBody.class);
        Intent intent = new Intent(activity, DisplayLyrics.class);;
        intent.putExtra(DisplayLyrics.LYRICS_BODY, body.lyrics_body);
        intent.putExtra(DisplayLyrics.TRACK_NAME, this.getParams().get(MusixmatchQueryParameter.q_track));
        intent.putExtra(DisplayLyrics.ARTIST_NAME, this.getParams().get(MusixmatchQueryParameter.q_artist));
        activity.startActivity(intent);
    }
}
