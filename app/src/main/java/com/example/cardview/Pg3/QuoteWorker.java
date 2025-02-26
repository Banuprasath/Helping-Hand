package com.example.cardview.Pg3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class QuoteWorker extends Worker {

    public QuoteWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        showNotification();
        return Result.success();
    }

    private void showNotification() {
        Context context = getApplicationContext();
        String channelId = "quote_channel";

        // Create notification channel (needed for Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Daily Quotes",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Motivation Quote")
                .setContentText("Believe in yourself! Tap to see more.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Check for notification permission
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        if (managerCompat.areNotificationsEnabled()) {
            managerCompat.notify(1, builder.build());
        }
    }
}
