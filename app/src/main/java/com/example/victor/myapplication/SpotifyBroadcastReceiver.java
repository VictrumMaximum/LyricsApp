package com.example.victor.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class SpotifyBroadcastReceiver extends BroadcastReceiver {
    private static final String NOTIFICATION_TITLE = "Lyrics for spotify";
    private static final String NOTIFICATION_TEXT = "Text";
    private static final int NOTIFICATION_ID = 0;
    private static final String NOTIFICATION_CHANNEL_ID = "com.example.victor.myapplication.lyricsForSpotify";
    private static final String NOTIFICATION_CHANNEL_NAME = "lyricsForSpotify";
    private static final String NOTIFICATION_CHANNEL_DESCRIPTION = "Notifications for the lyrics app for Spotify";
    private static final int NOTIFICATION_PRIORITY_LEVEL = NotificationManager.IMPORTANCE_DEFAULT;

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("on receive");
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        Log.d("BROADCAST RECEIVED", action);
        /*
        switch (action) {
            case
            case default:
        }
        this.createNotificationChannel(context);
//        String trackId = intent.getStringExtra("id");
//        String artistName = intent.getStringExtra("artist");
//        String albumName = intent.getStringExtra("album");
        String trackName = intent.getStringExtra("track");
//        int trackLengthInSec = intent.getIntExtra("length", 0);

        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.preference_file_track_key), trackName);
        editor.apply(); // maybe commit instead of apply?

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(context, DisplayLyrics.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent dismissEvent = new Intent(context, SpotifyBroadcastReceiver.class);
        dismissEvent.setAction(context.AC);
        dismissEvent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, dismissEvent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
//                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(NOTIFICATION_TEXT)
                .setContentIntent(resultPendingIntent)
                .setPriority(NOTIFICATION_PRIORITY_LEVEL);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTIFICATION_ID, mBuilder.build());
        */
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NOTIFICATION_PRIORITY_LEVEL);
            channel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}