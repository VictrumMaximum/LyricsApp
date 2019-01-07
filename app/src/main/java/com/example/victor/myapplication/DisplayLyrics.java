package com.example.victor.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.victor.myapplication.query.apiSeeds.APITask;
import com.example.victor.myapplication.query.apiSeeds.ApiSeedsResponse;


public class DisplayLyrics extends ActivityWithApiTask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lyrics);
        // TODO: check for intent bundle from MainActivity first? Maybe not, need to check for cached result in sharedPref anyway.
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), MODE_PRIVATE);
        String trackName = sharedPref.getString(getString(R.string.preference_file_track_key), "No track name");
        String artistName = sharedPref.getString(getString(R.string.preference_file_artist_key), "No artist name");
        String lyricsBody = "No api key provided";
        if (sharedPref.contains(getString(R.string.preference_file_api_key))) { // check for api key
            if (!sharedPref.contains(getString(R.string.preference_file_cached_key))) { // check for cached result
                String apiKey = sharedPref.getString(getString(R.string.preference_file_api_key), ""); // at this point sure to be there
                this.asyncTask = new APITask(this, apiKey, artistName, trackName);
                this.asyncTask.execute();
                return;
            }
            Log.d("DisplayActivity", "Got lyrics from cache");
            lyricsBody = sharedPref.getString(getString(R.string.preference_file_cached_key), ""); // at this point sure to be there
        }
        this.showLyrics(artistName, trackName, lyricsBody);
    }

    @Override
    protected void onDestroy() {
        Log.d("DisplayActivity", "Destroy");
        if (asyncTask != null && !this.asyncTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            this.asyncTask.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void callback(ApiSeedsResponse asr) {
        super.callback(asr);
        if (asr == null) {
            Log.e("DisplayActivity", "APITask reponse is null"); //TODO: alert user
            return;
        }
        // save result text as cache
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.preference_file_cached_key), asr.result.track.text);
        editor.apply();

        this.showLyrics(asr.result.artist.name, asr.result.track.name, asr.result.track.text);
    }

    private void showLyrics(String artist, String track, String body) {
        ((TextView) findViewById(R.id.lyricsHeader)).
                        setText(track + ", " + artist);
        ((TextView) findViewById(R.id.lyricsBody)).setText(body);
    }
}
