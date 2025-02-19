package com.example.cardview.pg2;

import android.annotation.SuppressLint;
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

public class pg2Page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg2_page1);

        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // HTML Content for Explanation
        String htmlContent = "<h2><b>Develop an Application Using GUI Components, Fonts, and Colors</b></h2>" +
                "<ul>" +
                "<li>Open <b>Android Studio</b> and create a new project.</li>" +
                "<li>Select <b>Empty Activity</b> and set the language to <b>Java</b>.</li>" +
                "<li>Design the UI using <b>XML</b> and add GUI components like Buttons, EditText, and TextView.</li>" +
                "<li>Customize fonts using <b>Typeface</b> and apply different styles (Bold, Italic).</li>" +
                "<li>Change colors dynamically using <b>ColorPicker</b> or predefined color values.</li>" +
                "<li>Use <b>onClick Listeners</b> to handle button actions.</li>" +
                "<li>Apply different background colors to enhance UI appearance.</li>" +
                "<li><b>Run</b> the app and test all features.</li>" +
                "</ul><br>" +

                "<h2><b>Algorithm for Implementation:</b></h2>" +
                "<ol>" +
                "<li>Create an <b>XML Layout</b> with TextView, EditText, and Buttons.</li>" +
                "<li>Use <b>TextView</b> to display user input and changes in color/fonts.</li>" +
                "<li>Use <b>onClickListeners</b> for buttons to change font size, color, and style.</li>" +
                "<li>Apply <b>Typeface</b> to modify font appearance.</li>" +
                "<li>Use predefined colors or allow users to select using a ColorPicker.</li>" +
                "<li>Test all functionalities.</li>" +
                "</ol><br>" +

                "<h2><b>References:</b></h2>" +
                "<ul>" +
                "<li><a href='https://developer.android.com/guide/topics/ui/controls'><i>Android UI Components</i></a></li>" +
                "<li><a href='https://developer.android.com/training/custom-views'><i>Customizing Views in Android</i></a></li>" +
                "</ul>";

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

        // Enable scrolling for long content
        textView.setMovementMethod(android.text.method.ScrollingMovementMethod.getInstance());

        // Enable clickable links
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Button to navigate to GUI Example Activity
        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(v -> {
            Intent i = new Intent(pg2Page1.this, pg2Page2.class);
            startActivity(i);
        });
    }
}
