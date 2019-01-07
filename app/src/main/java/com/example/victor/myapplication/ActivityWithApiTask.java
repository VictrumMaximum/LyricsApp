package com.example.victor.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.victor.myapplication.query.apiSeeds.ApiSeedsResponse;

public abstract class ActivityWithApiTask extends AppCompatActivity {
    protected AsyncTask<Void, Void, ApiSeedsResponse> asyncTask;

    public void callback(ApiSeedsResponse asr) {
        this.asyncTask = null;

    }
}
