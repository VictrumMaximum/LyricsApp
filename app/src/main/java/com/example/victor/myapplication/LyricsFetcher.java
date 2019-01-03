package com.example.victor.myapplication;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class LyricsFetcher extends AsyncTask<String, Character, Void> {

    private WeakReference<MainActivity> activity;

    LyricsFetcher(WeakReference<MainActivity> activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected Void doInBackground(String... strings) {
        char[] chars = strings[0].toCharArray();
        System.out.println(strings[1]); // param2
        while (true) {
            if (isCancelled()) {
                System.out.println("cancelled");
                return null;
            }
            publishProgress();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Character... chars) {
        System.out.println("On Progress Update");
        this.activity.get().test();
//        ((TextView) this.activity.get().findViewById(R.id.textView2)).append(chars[0].toString());
    }

    @Override
    protected void onPostExecute(Void v) {
        System.out.println("On Post Execute");
    }
}
