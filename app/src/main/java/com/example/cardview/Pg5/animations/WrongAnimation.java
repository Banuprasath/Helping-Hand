package com.example.cardview.Pg5.animations;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.example.cardview.R;

public class WrongAnimation {
    private Context context;

    public WrongAnimation(Context context) {
        this.context = context;
    }

    // Method to apply wrong animation
    public void startAnimation(Button clickedButton) {
        // Shake effect
        ObjectAnimator shake = ObjectAnimator.ofFloat(clickedButton, "translationX", 0, 10, -10, 10, -10, 0);
        shake.setDuration(300);
        shake.start();
    }

    // Method to apply flash red animation
    public void startAnimation(ImageView wrongView) {
        wrongView.setImageResource(R.drawable.animation_wrong);
        AnimationDrawable wrongAnimation = (AnimationDrawable) wrongView.getDrawable();
        wrongAnimation.start();
        wrongView.setVisibility(View.VISIBLE);
    }
}
