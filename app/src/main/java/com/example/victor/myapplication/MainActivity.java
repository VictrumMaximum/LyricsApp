package com.example.victor.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private String apiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        if (sharedPref.contains(getString(R.string.preference_file_api_key))) {
            this.apiKey = sharedPref.getString(getString(R.string.preference_file_api_key), "");
            EditText editText = findViewById(R.id.apiKey);
            editText.setText(this.apiKey);
            editText.setEnabled(false);
        }
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

    public void getFromSpotify(View view) {
        startActivity(new Intent(this, DisplayLyrics.class));
    }

    public void sendRequest(View view) {
        Log.d("MainActivity", "sendRequest");
        String apiKey = ((EditText)findViewById(R.id.apiKey)).getText().toString();
        String track = "Sexy and i know it";//((EditText)findViewById(R.id.track)).getText().toString();
        String artist = "LMFAO";//((EditText)findViewById(R.id.artist)).getText().toString();

        // TODO: ignoring user input for now, automatically search spotify lyrics
        startActivity(new Intent(this, DisplayLyrics.class));
    }

    public void saveApiKey(View view) {
        Log.d("MainActivity", "save api key");
        EditText apiKeyField = findViewById(R.id.apiKey);

        String apiKey = apiKeyField.getText().toString();
        System.out.println("current: " + this.apiKey);
        System.out.println("new: " + apiKey);
        if (!apiKey.equals(this.apiKey)) {
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preference_file_api_key), apiKey);
            editor.apply();
        }
        view.setVisibility(View.INVISIBLE);
        findViewById(R.id.apiKeyEditButton).setVisibility(View.VISIBLE);
        apiKeyField.setEnabled(false);
    }

    public void editApiKey(View view) {
        EditText apiKeyField = findViewById(R.id.apiKey);
        view.setVisibility(View.INVISIBLE);
        findViewById(R.id.apiKeySaveButton).setVisibility(View.VISIBLE);
        apiKeyField.setEnabled(false);
        apiKeyField.setEnabled(true);
    }
}
