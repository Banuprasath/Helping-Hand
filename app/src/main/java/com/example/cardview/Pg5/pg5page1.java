package com.example.cardview.Pg5;

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

import com.example.cardview.Pg4.Calculator;
import com.example.cardview.Pg4.pg4Page1;
import com.example.cardview.R;

public class pg5page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pg5page1);


        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Ensure the ID exists in XML

        // Set the HTML content using Html.fromHtml()
        String htmlContent = " "+
                "<h2><b> Procedure to Create a Quiz App with Confetti in Android Studio:</b></h2>  \n" +
                "<ul>  \n" +
                "<li>Open <b>Android Studio</b> and create a new project.</li>  \n" +
                "<li>Select <b>Empty Activity</b> and set the language to <b>Java</b>.</li>  \n" +
                "<li>Create a new Java class (e.g., <b>QuizActivity.java</b>) and design the layout using <b>activity_quiz.xml</b>.</li>  \n" +
                "<li>Use <b>TextView</b> to show the question and <b>Button</b>s for 4 options.</li>  \n" +
                "<li>Add a <b>LottieAnimationView</b> in the layout to show confetti for correct answers.</li>  \n" +
                "<li>Create a <b>QuestionBank.java</b> file to hold a 2D string array of questions and answers.</li>  \n" +
                "<li>Write Java code to randomly display questions and handle answer selection.</li>  \n" +
                "<li>Use <b>ObjectAnimator</b> to shake the button when the answer is wrong.</li>  \n" +
                "<li>Use <b>confettiView.playAnimation()</b> to show confetti when the answer is right.</li>  \n" +
                "<li><b>Run</b> the app and test the quiz flow and animations.</li>  \n" +
                "</ul><br>\n" +
                "\n" +
                "<h2><b>Algorithm to Handle Quiz Logic and Confetti:</b></h2>  \n" +
                "<ol>  \n" +
                "<li>Store all quiz questions in a 2D array format:  \n" +
                "    <br><i>{\"Question\", \"Option1\", \"Option2\", \"Option3\", \"Option4\", \"CorrectAnswer\"}</i></li>  \n" +
                "<li>When the activity starts, load a random question using <b>Random</b> class.</li>  \n" +
                "<li>Display the question and options on the screen using <b>TextView</b> and <b>Buttons</b>.</li>  \n" +
                "<li>On button click:  \n" +
                "    <ul>  \n" +
                "        <li>If the selected option matches the correct answer, play confetti animation.</li>  \n" +
                "        <li>Else, shake the clicked button using <b>ObjectAnimator</b>.</li>  \n" +
                "    </ul>  \n" +
                "</li>  \n" +
                "<li>After the answer is selected, load the next random question.</li>  \n" +
                "<li>Repeat until the user finishes all questions or as long as desired.</li>  \n" +
                "</ol><br>\n" +
                "\n" +
                "<h2><b>Dependencies Required:</b></h2>  \n" +
                "<ul>  \n" +
                "<li>Add Lottie dependency to <b>build.gradle (app level)</b>:  \n" +
                "<pre><code>implementation 'com.airbnb.android:lottie:5.2.0'</code></pre>  \n" +
                "</li>  \n" +
                "<li>Download and add a confetti JSON animation from <a href=\"https://lottiefiles.com\">LottieFiles</a>.</li>  \n" +
                "</ul><br>\n" +
                "\n" +
                "<h2><b>References:</b></h2>  \n" +
                "<ul>  \n" +
                "<li><a href='https://developer.android.com/studio'><i>Official Android Studio Documentation</i></a></li>  \n" +
                "<li><a href='https://lottiefiles.com'><i>Lottie Animations</i></a></li>  \n" +
                "<li><a href='https://developer.android.com/reference/android/animation/ObjectAnimator'><i>ObjectAnimator for Animations</i></a></li>  \n" +
                "<li><a href='YOUR_REFERENCE_LINK'><i>Your Additional Reference Link</i></a></li>  \n" +
                "</ul>  \n" +
                "\n" +

                "\n+ ";

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
                Intent i = new Intent(pg5page1.this, QuizActivity.class);
                startActivity(i);
            }
        });
    }
}