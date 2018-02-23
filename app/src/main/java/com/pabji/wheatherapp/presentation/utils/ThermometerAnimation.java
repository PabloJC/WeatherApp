package com.pabji.wheatherapp.presentation.utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;


public class ThermometerAnimation extends Animation {

    private ProgressBar progressBar;
    private float from;
    private float to;

    public ThermometerAnimation(ProgressBar progressBar, float from, float to) {
        super();
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;

        if (value < 15) {
            progressBar.getProgressDrawable().setColorFilter(progressBar.getResources().getColor(android.R.color.holo_blue_light), android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (value >= 15 && value < 30) {
            progressBar.getProgressDrawable().setColorFilter(progressBar.getResources().getColor(android.R.color.holo_orange_light), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            progressBar.getProgressDrawable().setColorFilter(progressBar.getResources().getColor(android.R.color.holo_red_light), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        progressBar.setProgress((int) value);
    }
}

