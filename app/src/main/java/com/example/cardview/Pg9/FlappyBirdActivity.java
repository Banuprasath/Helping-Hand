package com.example.cardview.Pg9;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FlappyBirdActivity extends AppCompatActivity {

    FlappyBirdView flappyBirdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flappyBirdView = new FlappyBirdView(this);
        setContentView(flappyBirdView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flappyBirdView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flappyBirdView.pause();
    }
}


