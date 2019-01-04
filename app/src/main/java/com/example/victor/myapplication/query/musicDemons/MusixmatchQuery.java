package com.example.victor.myapplication.query.musicDemons;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

public abstract class MusixmatchQuery {
    private Map<MusixmatchQueryParameter, String> params;
    Activity activity;
    MusixmatchQuery(Activity activity) {
        this.params = new HashMap<>();
        this.activity = activity;
    }
    public void addParam(MusixmatchQueryParameter key, String value) {
        params.put(key, value);
    }
    Map<MusixmatchQueryParameter, String> getParams() {
        return this.params;
    }
    abstract String getType();
    abstract void success(MusixmatchResponse mmr);
}
