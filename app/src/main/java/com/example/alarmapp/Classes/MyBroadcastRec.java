package com.example.alarmapp.Classes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alarmapp.R;

public class MyBroadcastRec extends BroadcastReceiver {
    public static final String channel_id = "Channel";
    public static final String channel_name = "myChannel";
    @Override
    public void onReceive(Context context, Intent intent) {

        MediaPlayer mediaPlayer ;
        mediaPlayer = MediaPlayer.create(context, R.raw.audio);
        mediaPlayer.start();
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(10000);
        Toast.makeText(context, "alarm set successfully", Toast.LENGTH_LONG).show();
        NotificationChannel notificationChannel;
        NotificationManager manager;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);

        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My App")
                .setContentText("this is your app you can use it for free")
                .setAutoCancel(true)
        ;
        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        compat.notify(1, builder.build());
//        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
//        compat.cancel(1);
    }
}
