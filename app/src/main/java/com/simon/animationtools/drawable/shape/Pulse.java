package com.simon.animationtools.drawable.shape;

import android.animation.ValueAnimator;

import com.simon.animationtools.utils.DrawableAnimatorBuilder;

/**
 * Created by simon on 17-6-7.
 */

public class Pulse extends CircleAnimDrawable {

    public Pulse() {
        setScale(0f);
    }

    @Override
    public ValueAnimator createAnimator() {
        float fractions[] = new float[]{0f, 1f};
        return new DrawableAnimatorBuilder(this)
                .scale(fractions, 0f, 1f)
                .alpha(fractions, 1f, 0f)
                .duration(1000)
                .easeInOut(fractions)
                .build();
    }

}
