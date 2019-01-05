package com.example.victor.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayLyrics extends AppCompatActivity {
    public static final String LYRICS_BODY = "com.example.victor.myapplication.LYRICS_BODY";
    public static final String TRACK_NAME = "com.example.victor.myapplication.TRACK_NAME";
    public static final String ARTIST_NAME = "com.example.victor.myapplication.ARTIST_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lyrics);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            System.out.println("bundle != null");
            if (bundle.containsKey(LYRICS_BODY)) {
                ((TextView) findViewById(R.id.lyricsBody)).setText(bundle.getString(LYRICS_BODY));
            }
            if (bundle.containsKey(ARTIST_NAME) && bundle.containsKey(TRACK_NAME)) {
                ((TextView) findViewById(R.id.lyricsHeader)).
                        setText(bundle.getString(TRACK_NAME) + ", " + bundle.getString(ARTIST_NAME));
            }
        }
    }
}
