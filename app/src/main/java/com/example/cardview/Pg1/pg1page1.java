package com.example.cardview.Pg1;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.cardview.R;

public class pg1page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg1page1);

        AppCompatTextView textView = findViewById(R.id.textView);

        // Set the HTML content using Html.fromHtml()
        String htmlContent = "<b>Installing and Configuring Java Development Kit (JDK):</b><br>" +
                "1. Download the JDK installer from the official Oracle website: " +
                "<a href='https://www.oracle.com/java/technologies/javase-jdk11-downloads.html'>" +
                "<i>https://www.oracle.com/java/technologies/javase-jdk11-downloads.html</i></a><br>" +
                "2. Run the installer and follow the on-screen instructions.<br>" +
                "3. Set the <b>JAVA_HOME</b> environment variable to the JDK directory.<br>" +
                "4. Add the JDK bin folder to the <b>PATH</b> environment variable for command-line access.<br>" +
                "<b>Installing Android Studio:</b><br>" +
                "1. Go to the official Android Studio website: <a href='https://developer.android.com/studio'>" +
                "<i>https://developer.android.com/studio</i></a><br>" +
                "2. Download the installer for your operating system (Windows, macOS, or Linux).<br>" +
                "3. Follow the installation guide to complete the setup process.<br>" +
                "4. After installation, open Android Studio and complete the initial setup wizard.<br>" +
                "<b>Configuring Android SDK:</b><br>" +
                "1. Open Android Studio.<br>" +
                "2. Go to <b>File > Settings > Appearance & Behavior > System Settings > Android SDK</b>.<br>" +
                "3. In the SDK Platforms tab, ensure the latest SDK version is selected.<br>" +
                "4. In the SDK Tools tab, ensure that Android SDK Build-Tools, Android SDK Platform-Tools, and Android SDK Tools are installed.<br>" +
                "5. Click on <i>OK</i> to save the settings.<br>" +
                "<b>Notes:</b><br>" +
                "- Make sure your system meets the minimum system requirements for Android Studio.<br>" +
                "- Update Android Studio and the SDK regularly for the latest features and bug fixes.";

        // Apply the HTML content
        textView.setText(Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY));

        // Set text color to white
        textView.setTextColor(Color.WHITE);

        // Set text size
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        // Set background color
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor));

        // Set padding
        textView.setPadding(16, 16, 16, 16);

        // Enable scrolling
        textView.setMovementMethod(android.text.method.ScrollingMovementMethod.getInstance());

        // Enable clickable links
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
