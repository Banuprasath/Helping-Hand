package com.example.cardview.Pg3;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.cardview.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MotivationQuote extends AppCompatActivity {

    private TextView quoteText;
    private String[] quotes = {
            "Believe in yourself!",
            "Stay positive, work hard, make it happen.",
            "Dream big, work hard, stay focused.",
            "Success is not the key to happiness. Happiness is the key to success.",
            "Push yourself, because no one else is going to do it for you."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_motivation_quote);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quoteText = findViewById(R.id.quoteText);
        Button btnNewQuote = findViewById(R.id.btnNewQuote);

        // Request Notification Permission
        requestNotificationPermission();

        btnNewQuote.setOnClickListener(v -> {
            displayRandomQuote();
            sendInstantNotification();
        });

        scheduleDailyNotification();
    }

    private void displayRandomQuote() {
        Random random = new Random();
        String newQuote = quotes[random.nextInt(quotes.length)];
        quoteText.setText(newQuote);
    }

    private void scheduleDailyNotification() {
        PeriodicWorkRequest dailyWorkRequest = new PeriodicWorkRequest.Builder(QuoteWorker.class, 6, TimeUnit.HOURS)
                .build();
        WorkManager.getInstance(this).enqueue(dailyWorkRequest);
    }

    private void sendInstantNotification() {
        String channelId = "instant_quote_channel";
        String quote = quoteText.getText().toString();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Instant Quotes", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Your Quote")
                .setContentText(quote)
                .setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        // Check permission before sending notification
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            manager.notify(2, builder.build());
        } else {
            Toast.makeText(this, "Notification permission required!", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
