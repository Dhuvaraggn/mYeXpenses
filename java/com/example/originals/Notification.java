package com.example.originals;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;


public class Notification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        Intent notifyintent = new Intent(context, Todayex_main.class);
        PendingIntent contintent = PendingIntent.getActivities(context, 0, new Intent[]{notifyintent}, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Today MyeXpenses",NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("submit your today expenses");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
           manager.createNotificationChannel(notificationChannel);

        }


    }


}
