package com.example.cardview.Pg9;

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

public class pg9page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pg8page8);


        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Ensure the ID exists in XML

        // Set the HTML content using Html.fromHtml()
        String htmlContent =

                "<body>\n" +
                "\n" +
                "<h2><b>Simple Procedure to Create a Flappy Bird App in Android Studio (Java):</b></h2>\n" +
                "<ul>\n" +
                "    <li>Open <b>Android Studio</b> and create a new project.</li>\n" +
                "    <li>Select <b>Empty Activity</b> and set the language to <b>Java</b>.</li>\n" +
                "    <li>Create a custom class extending <b>SurfaceView</b> for game rendering (e.g., <b>GameView.java</b>).</li>\n" +
                "    <li>Use <b>Canvas</b> and <b>Paint</b> to draw background, bird, and pipes.</li>\n" +
                "    <li>Place game assets like <code>bird.png</code>, <code>background.png</code>, and <code>pipe.png</code> inside <code>assets/</code>.</li>\n" +
                "    <li>Load images and sounds in the game thread using <b>BitmapFactory</b> and <b>SoundPool</b>.</li>\n" +
                "    <li>Implement basic physics for bird jump and gravity.</li>\n" +
                "    <li>Detect collisions between bird and pipes to end the game.</li>\n" +
                "    <li>Display score and play sound on success or game over.</li>\n" +
                "</ul>\n" +
                "\n" +
                "<br>\n" +
                "\n" +
                "<h2><b>Algorithm for Flappy Bird Game Logic:</b></h2>\n" +
                "<ol>\n" +
                "    <li>Start the game loop using a thread.</li>\n" +
                "    <li>On each frame:\n" +
                "        <ul>\n" +
                "            <li>Clear the canvas and redraw the background.</li>\n" +
                "            <li>Update bird’s vertical position using gravity.</li>\n" +
                "            <li>If the user touches the screen, apply a negative velocity (flap).</li>\n" +
                "            <li>Move the pipes horizontally to the left.</li>\n" +
                "            <li>Check if the bird intersects with any pipe:\n" +
                "                <ul>\n" +
                "                    <li>If yes, end the game and play hit sound.</li>\n" +
                "                </ul>\n" +
                "            </li>\n" +
                "            <li>If the bird successfully passes a pipe pair:\n" +
                "                <ul>\n" +
                "                    <li>Increase score and play point sound.</li>\n" +
                "                </ul>\n" +
                "            </li>\n" +
                "            <li>Loop pipes to the right side after they go off screen.</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>Draw score and game state (ready, playing, game over).</li>\n" +
                "</ol>\n" +
                "\n" +
                "<br>\n" +
                "\n" +
                "<h2><b>References:</b></h2>\n" +
                "<ul>\n" +
                "    <li><a href=\"https://developer.android.com/reference/android/view/SurfaceView\" target=\"_blank\">Android SurfaceView</a></li>\n" +
                "    <li><a href=\"https://developer.android.com/guide/topics/media/soundpool\" target=\"_blank\">SoundPool API</a></li>\n" +
                "    <li><a href=\"https://github.com/samuelcust/flappy-bird-assets\" target=\"_blank\">Flappy Bird Assets</a></li>\n" +
                "</ul> </body>";

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
                Intent i = new Intent(pg9page1.this, FlappyBirdActivity.class);
                startActivity(i);
            }
        });

    }
}