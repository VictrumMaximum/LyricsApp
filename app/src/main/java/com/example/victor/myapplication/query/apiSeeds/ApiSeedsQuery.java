package com.example.victor.myapplication.query.apiSeeds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.victor.myapplication.DisplayLyrics;
import com.example.victor.myapplication.MainActivity;

public class ApiSeedsQuery  {
    private String apiKey;
    private String artist;
    private String track;
    public ApiSeedsQuery(String artist, String track, String apiKey) {
        this.artist = artist;
        this.track = track;
        this.apiKey = apiKey;
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
}
