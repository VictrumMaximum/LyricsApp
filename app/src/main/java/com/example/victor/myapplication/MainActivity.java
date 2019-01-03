package com.example.victor.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    private LyricsFetcher lyricsFetcherThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lyricsFetcherThread = new LyricsFetcher(new WeakReference<>(this));
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
//        this.lyricsFetcherThread.cancel(true);
        super.onDestroy();
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        this.lyricsFetcherThread.execute("lyrics", "param2");
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
        this.finish();
    }

    public void test() {
        System.out.println("progress update");
    }
}
