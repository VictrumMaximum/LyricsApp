package com.example.victor.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.victor.myapplication.query.apiSeeds.APITask;
import com.example.victor.myapplication.query.apiSeeds.ApiSeedsResponse;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "Resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", "Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity", "Stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "Destroy");
        super.onDestroy();
    }

    public void sendRequest(View view) {
        Log.d("MainActivity", "sendRequest");
        String apiKey = ((EditText)findViewById(R.id.apiKey)).getText().toString();
        String track = "Sexy and i know it";//((EditText)findViewById(R.id.track)).getText().toString();
        String artist = "LMFAO";//((EditText)findViewById(R.id.artist)).getText().toString();

        // TODO: ignoring user input for now, automatically search spotify lyrics
        startActivity(new Intent(this, DisplayLyrics.class));
    }
}
