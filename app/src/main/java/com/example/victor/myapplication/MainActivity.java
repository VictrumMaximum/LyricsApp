package com.example.victor.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.victor.myapplication.query.musixmatch.LyricsFetcher;
import com.example.victor.myapplication.query.musixmatch.MusixmatchQuery;
import com.example.victor.myapplication.query.musixmatch.TrackSearchQuery;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private LyricsFetcher lyricsFetcherTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        System.out.println("on resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("on pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("on stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("on destroy");
        if (this.lyricsFetcherTask != null && !this.lyricsFetcherTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            this.lyricsFetcherTask.cancel(true);
        }
        super.onDestroy();
    }

    public void sendRequest(View view) {
        // Do not execute if there is a task already active.
        // lyricsFetcherTask will be set to null in the callback setText(String str)
        if (this.lyricsFetcherTask != null) {
            return;
        }
        String apiKey = ((EditText)findViewById(R.id.apiKey)).getText().toString();
        String track = ((EditText)findViewById(R.id.track)).getText().toString();
        String artist = ((EditText)findViewById(R.id.artist)).getText().toString();
        String album = ((EditText)findViewById(R.id.album)).getText().toString();

        MusixmatchQuery mmq = new TrackSearchQuery(this);
        mmq.addParam("apikey", apiKey);
        mmq.addParam("q_track", track);
        mmq.addParam("q_artist", artist);
        this.lyricsFetcherTask = new LyricsFetcher(new WeakReference<>(this), mmq);
        this.lyricsFetcherTask.execute();
    }

    public void resetTask () {
        this.lyricsFetcherTask = null;
    }

    // callback function from lyricsFetchTask
//    public void setText(MusixmatchResponse mmr) {
//        TextView tw = findViewById(R.id.textView);
//        System.out.println(new Gson().toJson(mmr));
////        tw.setText(str);
//        this.lyricsFetcherTask = null;
//    }
//
//    // callback function from lyricsFetchTask
//    public void reportError(Exception error) {
//        error.printStackTrace();
//        this.lyricsFetcherTask = null;
//        // TODO: alert user with popup
//    }
}
