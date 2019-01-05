package com.example.victor.myapplication.query.apiSeeds;

import android.content.Intent;
import android.os.Bundle;

import com.example.victor.myapplication.DisplayLyrics;
import com.example.victor.myapplication.MainActivity;

public class ApiSeedsQuery  {
    private MainActivity activity;
    private String apiKey;
    private String artist;
    private String track;
    public ApiSeedsQuery(MainActivity activity, String artist, String track, String apiKey) {
        this.artist = artist;
        this.track = track;
        this.apiKey = apiKey;
        this.activity = activity;
    }
    public String getApiKey() {
        return apiKey;
    }
    public String getArtist() {
        return artist;
    }
    public String getTrack() {
        return track;
    }
    void done(ApiSeedsResponse asr) {
        activity.resetTask(); // TODO: find a way to make this automatic
        if (asr == null) {
            System.out.println("ALERT USER"); //TODO: alert user
            return;
        }
        Intent intent = new Intent(activity, DisplayLyrics.class);
        Bundle bundle = new Bundle();
        bundle.putString(DisplayLyrics.TRACK_NAME, asr.result.track.name);
        bundle.putString(DisplayLyrics.ARTIST_NAME, asr.result.artist.name);
        bundle.putString(DisplayLyrics.LYRICS_BODY, asr.result.track.text);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
