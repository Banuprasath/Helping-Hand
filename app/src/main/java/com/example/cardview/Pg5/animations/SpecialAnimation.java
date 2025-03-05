package com.example.cardview.Pg5.animations;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.content.Context;
import com.example.cardview.R;

public class SpecialAnimation {
    private Context context;

    public SpecialAnimation(Context context) {
        this.context = context;
    }

    public void startAnimation(View view) {
        Animation fadeInZoom = AnimationUtils.loadAnimation(context, R.anim.fade_zoom);
        view.startAnimation(fadeInZoom);
    }
}
