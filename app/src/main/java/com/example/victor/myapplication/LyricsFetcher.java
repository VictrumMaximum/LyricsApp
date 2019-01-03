package com.example.victor.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

public class LyricsFetcher extends AsyncTask<String, Character, Void> {

    private TextView textView;

    public LyricsFetcher(Activity activity) {
        this.textView = activity.findViewById(R.id.textView2);
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected Void doInBackground(String... strings) {
        char[] chars = strings[0].toCharArray();
        System.out.println(strings[1]); // param2
        for (char c: chars) {
            publishProgress(c);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Character... chars) {
        System.out.println("On Progress Update");
        textView.append(chars[0].toString());
    }

    @Override
    protected void onPostExecute(Void v) {
        System.out.println("On Post Execute");
    }
}
