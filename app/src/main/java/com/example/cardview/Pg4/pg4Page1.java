package com.example.cardview.Pg4;

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

public class pg4Page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg4page1);

        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Ensure the ID exists in XML

        // Set the HTML content using Html.fromHtml()
        String htmlContent = "<h2><b>Simple Procedure to Create a Calculator in Android Studio:</b></h2>" +
                "<ul>" +
                "<li>Open <b>Android Studio</b> and create a new project.</li>" +
                "<li>Select <b>Empty Activity</b> and set the language to <b>Java</b>.</li>" +
                "<li>Design the calculator layout using <b>XML</b> (<i>LinearLayout or ConstraintLayout</i>).</li>" +
                "<li>Add buttons for numbers (0-9) and operators (+, -, *, /, =, AC, ⌫).</li>" +
                "<li>Create a <b>TextView</b> to display user input and results.</li>" +
                "<li>Write Java code in <b>MainActivity.java</b> to handle button clicks and perform calculations.</li>" +
                "<li>Use a <b>StringBuilder</b> to store user input and evaluate expressions.</li>" +
                "<li>Implement <b>onClick listeners</b> for all buttons to update the display.</li>" +
                "<li>Use <b>eval()</b> or a custom function to evaluate expressions when '=' is clicked.</li>" +
                "<li><b>Run</b> the app and test all functionalities.</li>" +
                "</ul><br>" +

                "<h2><b>Algorithm to Perform Calculations:</b></h2>" +
                "<ol>" +
                "<li>Initialize an empty string to store input.</li>" +
                "<li>When a number or operator button is clicked, append it to the input string.</li>" +
                "<li>When <b>AC</b> is clicked, clear the input string.</li>" +
                "<li>When ⌫ (<i>Backspace</i>) is clicked, remove the last character.</li>" +
                "<li>When '=' is clicked:</li>" +
                "<ul>" +
                "<li>Parse the input string.</li>" +
                "<li>Perform calculations using <b>Java's ScriptEngineManager</b> or custom logic.</li>" +
                "<li>Display the result in the TextView.</li>" +
                "</ul>" +
                "<li>Handle errors such as division by zero or invalid input.</li>" +
                "</ol><br>" +

                "<h2><b>References:</b></h2>" +
                "<ul>" +
                "<li><a href='https://developer.android.com/studio'><i>Official Android Studio Documentation</i></a></li>" +
                "<li><a href='https://developer.android.com/training/basics/user-interface/controls'><i>Android UI Components</i></a></li>" +
                "<li><a href='YOUR_REFERENCE_LINK'><i>Your Additional Reference Link</i></a></li>" +
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

        // Enable scrolling
        textView.setMovementMethod(android.text.method.ScrollingMovementMethod.getInstance());

        // Enable clickable links
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pg4Page1.this,Calculator.class);
                startActivity(i);
            }
        });
    }
}
