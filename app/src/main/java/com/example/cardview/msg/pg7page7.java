package com.example.cardview.msg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cardview.Pg6.MainActivity;
import com.example.cardview.Pg6.pg6page1;
import com.example.cardview.R;

public class pg7page7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pg7page7);

        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Ensure the ID exists in XML

        // Set the HTML content using Html.fromHtml()
        String htmlContent ="<h2><b> Procedure to Send SMS in Android Studio:</b></h2>\n" +
                "<ul>\n" +
                "    <li>Open <b>Android Studio</b> and create a new project.</li>\n" +
                "    <li>Select <b>Empty Activity</b> and set the language to <b>Java</b>.</li>\n" +
                "    <li>Design the UI layout using <b>XML</b> with two <b>EditText</b> fields (for phone number and message) and a <b>Button</b> to send the SMS.</li>\n" +
                "    <li>In the <b>AndroidManifest.xml</b>, add the permission: <code>&lt;uses-permission android:name=\"android.permission.SEND_SMS\" /&gt;</code></li>\n" +
                "    <li>In your <b>Activity</b> (e.g., <code>sendSms.java</code>), use <b>findViewById()</b> to access the EditText and Button.</li>\n" +
                "    <li>Use <b>SmsManager</b> in Java code to send the SMS.</li>\n" +
                "    <li>Check and request SMS permission using <b>ContextCompat</b> and <b>ActivityCompat</b>.</li>\n" +
                "    <li>Implement the <b>onClickListener</b> for the send button to trigger the SMS sending logic.</li>\n" +
                "    <li>Use <b>Toast</b> to show messages for success or failure.</li>\n" +
                "    <li>Test the app on a real device (emulators usually cannot send SMS).</li>\n" +
                "</ul><br>\n" +
                "\n" +
                "<h2><b>Algorithm to Send SMS:</b></h2>\n" +
                "<ol>\n" +
                "    <li>User enters the phone number and message in EditText fields.</li>\n" +
                "    <li>When <b>Send</b> button is clicked:</li>\n" +
                "    <ul>\n" +
                "        <li>Validate that both fields are not empty.</li>\n" +
                "        <li>Check if <b>SEND_SMS</b> permission is granted.</li>\n" +
                "        <li>If not granted, request permission.</li>\n" +
                "        <li>If granted, call <b>SmsManager.getDefault().sendTextMessage()</b> with the given phone number and message.</li>\n" +
                "        <li>Display success or failure message using <b>Toast</b>.</li>\n" +
                "    </ul>\n" +
                "    <li>Handle permission result in <code>onRequestPermissionsResult()</code>.</li>\n" +
                "</ol><br>\n" +
                "\n" +
                "<h2><b>References:</b></h2>\n" +
                "<ul>\n" +
                "    <li><a href='https://developer.android.com/reference/android/telephony/SmsManager'><i>SmsManager Documentation</i></a></li>\n" +
                "    <li><a href='https://developer.android.com/training/permissions/requesting'><i>Requesting Permissions at Runtime</i></a></li>\n" +
                "    <li><a href='https://developer.android.com/studio'><i>Official Android Studio Guide</i></a></li>\n" +
                "</ul>\n";

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
        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pg7page7.this,
                        sendSms.class);
                startActivity(i);
            }
        });

    }
}