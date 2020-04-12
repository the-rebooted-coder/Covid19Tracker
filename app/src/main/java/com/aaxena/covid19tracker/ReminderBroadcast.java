package com.aaxena.covid19tracker;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.graphics.Color.RED;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, Landing.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Reminder")
                .setSmallIcon(R.drawable.coronalog)
                .setContentTitle("Don't Fall Prey to Fake Information")
                .setContentText("Keep checking Covid Tracker to keep fake news at bay")
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setColor(RED)
                .setColorized(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Keep cheking Covid Tracker to keep fake news at bay"))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());

    }
}
