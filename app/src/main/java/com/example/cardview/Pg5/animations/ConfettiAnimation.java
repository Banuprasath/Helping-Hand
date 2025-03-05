package com.example.cardview.Pg5.animations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;
import com.example.cardview.R;

public class ConfettiAnimation {
    private ImageView confettiView;

    public ConfettiAnimation(Context context, ImageView confettiView) {
        this.confettiView = confettiView;
    }

    public void startAnimation() {
        confettiView.setVisibility(View.VISIBLE);
        confettiView.setBackgroundResource(R.drawable.animation_confetti);

        AnimationDrawable animation = (AnimationDrawable) confettiView.getBackground();
        animation.start();

        new Handler().postDelayed(() -> {
            animation.stop();
            confettiView.setVisibility(View.GONE);
        }, 2000);
    }
}
