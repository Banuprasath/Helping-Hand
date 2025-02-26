package com.example.cardview.Pg3;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.example.cardview.R;

public class Pg3page1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg3page1);

        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Set the HTML-styled content
        String htmlContent = "<h2><b>How Daily Quote Notifications Work:</b></h2>" +
                "<ul>" +
                "<li>The app schedules a notification <b>every day at 6 AM</b>.</li>" +
                "<li>A random motivational quote will be displayed in the notification.</li>" +
                "<li>You can also tap 'Get Quote' to see a new quote instantly.</li>" +
                "<li>Ensure <b>Notification Permission</b> is granted for Android 13+.</li>" +
                "</ul><br>" +

                "<h2><b>Technical Implementation:</b></h2>" +
                "<ol>" +
                "<li>Uses <b>AlarmManager</b> for exact scheduling at 6 AM.</li>" +
                "<li>Uses <b>BroadcastReceiver</b> to trigger notifications.</li>" +
                "<li>Uses <b>SharedPreferences</b> to track daily quote history.</li>" +
                "</ol><br>" +

                "<h2><b>References:</b></h2>" +
                "<ul>" +
                "<li><a href='https://developer.android.com/reference/android/app/AlarmManager'><i>AlarmManager Docs</i></a></li>" +
                "<li><a href='https://developer.android.com/training/scheduling/alarms'><i>Scheduling Alarms Guide</i></a></li>" +
                "</ul>";

        // Apply the HTML content
        textView.setText(Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY));
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor));
        textView.setPadding(16, 16, 16, 16);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        Button startNotificationButton = findViewById(R.id.button);
        startNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pg3page1.this, MotivationQuote.class);
                startActivity(intent);
            }
        });
    }
}