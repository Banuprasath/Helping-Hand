package com.example.cardview.Pg8;

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

public class pg8page8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pg8page8);


        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Ensure the ID exists in XML

        // Set the HTML content using Html.fromHtml()
        String htmlContent = "<h2><b>Simple Procedure to Create a Weather App in Android Studio:</b></h2>\n" +
                "<ul>\n" +
                "    <li>Open <b>Android Studio</b> and create a new project.</li>\n" +
                "    <li>Select <b>Empty Activity</b> and set the language to <b>Java</b>.</li>\n" +
                "    <li>Design the layout using <b>activity_weather.xml</b> with:</li>\n" +
                "    <ul>\n" +
                "        <li>A <b>TextView</b> for city name.</li>\n" +
                "        <li>A <b>TextView</b> for temperature.</li>\n" +
                "        <li>A <b>TextView</b> for weather condition.</li>\n" +
                "        <li>An <b>ImageView</b> as background depending on the weather.</li>\n" +
                "    </ul>\n" +
                "    <li>Add the following permissions in the <b>AndroidManifest.xml</b>:</li>\n" +
                "    <pre>\n" +
                "&lt;uses-permission android:name=\"android.permission.INTERNET\" /&gt;\n" +
                "&lt;uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" /&gt;\n" +
                "    </pre>\n" +
                "    <li>Use <b>FusedLocationProviderClient</b> to get the user’s current location.</li>\n" +
                "    <li>Make an HTTP request to <b>OpenWeatherMap API</b> using the latitude and longitude.</li>\n" +
                "    <li>Parse the <b>JSON</b> response to extract city name, temperature, and weather condition.</li>\n" +
                "    <li>Update the UI with the fetched data and change background image dynamically.</li>\n" +
                "    <li>Use <b>AsyncTask</b> to perform the network operation in the background.</li>\n" +
                "    <li>Run the app and allow location permission when prompted.</li>\n" +
                "</ul><br>\n" +
                "\n" +
                "<h2><b>Algorithm to Fetch and Display Weather Information:</b></h2>\n" +
                "<ol>\n" +
                "    <li>Check and request location permission.</li>\n" +
                "    <li>Use <b>FusedLocationProviderClient</b> to request current location updates.</li>\n" +
                "    <li>When location is received, get the <b>latitude</b> and <b>longitude</b>.</li>\n" +
                "    <li>Pass the coordinates to an <b>AsyncTask</b> that performs the API request:</li>\n" +
                "    <ul>\n" +
                "        <li>Open a connection to the <b>OpenWeatherMap API</b> using HTTP GET.</li>\n" +
                "        <li>Read the JSON response.</li>\n" +
                "    </ul>\n" +
                "    <li>Parse the JSON and extract:</li>\n" +
                "    <ul>\n" +
                "        <li><b>City name</b></li>\n" +
                "        <li><b>Temperature (°C)</b></li>\n" +
                "        <li><b>Weather condition (e.g., sunny, rainy)</b></li>\n" +
                "    </ul>\n" +
                "    <li>Display the extracted values in <b>TextView</b> elements.</li>\n" +
                "    <li>Change the <b>background image</b> in the <b>ImageView</b> according to the condition.</li>\n" +
                "    <li>Stop location updates once data is fetched.</li>\n" +
                "</ol><br>\n" +
                "\n" +
                "<h2><b>References:</b></h2>\n" +
                "<ul>\n" +
                "    <li><a href=\"https://developer.android.com/training/location\">Android Location Services</a></li>\n" +
                "    <li><a href=\"https://openweathermap.org/api\">OpenWeatherMap API Documentation</a></li>\n" +
                "    <li><a href=\"https://developer.android.com/reference/android/os/AsyncTask\">AsyncTask Reference</a></li>\n" +
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
                Intent i = new Intent(pg8page8.this, Weather.class);
                startActivity(i);
            }
        });

    }
}