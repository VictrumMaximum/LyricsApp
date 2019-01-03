package com.example.victor.myapplication;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class LyricsFetcher extends AsyncTask<String, Character, String> {

    private WeakReference<MainActivity> activity;

    LyricsFetcher(WeakReference<MainActivity> activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected String doInBackground(String... strings) {
        String song = strings[0];
        String result = "result";
        return result;
    }

    @Override
    protected void onPostExecute(String str) {
        System.out.println("On Post Execute");
        if (this.activity.get() != null) {
            this.activity.get().setText(str);
        }
    }
}
