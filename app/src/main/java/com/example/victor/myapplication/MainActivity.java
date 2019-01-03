package com.example.victor.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        if (!this.lyricsFetcherTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            this.lyricsFetcherTask.cancel(true);
        }
        super.onDestroy();
    }

    public void sendMessage(View view) {
        // Do not execute if there is a task already active.
        // lyricsFetcherTask will be set to null in the callback setText(String str)
        if (this.lyricsFetcherTask != null) {
            return;
        }
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();

        this.lyricsFetcherTask = new LyricsFetcher(new WeakReference<>(this));
        this.lyricsFetcherTask.execute(message);
    }

    // callback function from lyricsFetchThread
    public void setText(String str) {
        TextView tw = findViewById(R.id.textView2);
        tw.setText(str);
        this.lyricsFetcherTask = null;
    }
}
