package com.example.victor.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.victor.myapplication.query.apiSeeds.APITask;
import com.example.victor.myapplication.query.apiSeeds.ApiSeedsQuery;
import com.example.victor.myapplication.query.musixmatch.MatcherLyricsQuery;
import com.example.victor.myapplication.query.musixmatch.MusixmatchQuery;
import com.example.victor.myapplication.query.musixmatch.MusixmatchQueryParameter;


public class MainActivity extends AppCompatActivity {
    private APITask apiTask;

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
        if (this.apiTask != null && !this.apiTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            this.apiTask.cancel(true);
        }
        super.onDestroy();
    }

    public void sendRequest(View view) {
        // Do not execute if there is a task already active.
//        if (this.apiTask != null) {
//            return;
//        }
//        String apiKey = ((EditText)findViewById(R.id.apiKey)).getText().toString();
//        String track = "Sexy and i know it";//((EditText)findViewById(R.id.track)).getText().toString();
//        String artist = "LMFAO";//((EditText)findViewById(R.id.artist)).getText().toString();
////        String album = ((EditText)findViewById(R.id.album)).getText().toString();
//
//        ApiSeedsQuery asq = new ApiSeedsQuery(this, artist, track, apiKey);
//        this.apiTask = new APITask(asq);
//        this.apiTask.execute();
    }

    public void resetTask () {
        this.apiTask = null;
    }

    // callback function from lyricsFetchTask
//    public void setText(MusixmatchResponse mmr) {
//        TextView tw = findViewById(R.id.textView);
//        System.out.println(new Gson().toJson(mmr));
////        tw.setText(str);
//        this.apiTask = null;
//    }
//
//    // callback function from lyricsFetchTask
//    public void reportError(Exception error) {
//        error.printStackTrace();
//        this.apiTask = null;
//        // TODO: alert user with popup
//    }
}
