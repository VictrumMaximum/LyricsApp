package com.example.victor.myapplication.query.musixmatch;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

public abstract class MusixmatchQuery {
    private Map<String, String> params;
    Activity activity;
    protected MusixmatchQuery(Activity activity) {
        this.params = new HashMap<>();
        this.activity = activity;
    }
    public void addParam(String key, String value) {
        params.put(key, value);
    }
    Map<String, String> getParams() {
        return this.params;
    }
    abstract String getType();
    abstract void success(MusixmatchResponse mmr);
}
