package com.simon.animationtools.drawable.impl;

import android.graphics.Rect;

import com.simon.animationtools.drawable.RectAnimDrawable;

public class RotatingPlane extends RectAnimDrawable {

    @Override
    protected void onBoundsChange(Rect bounds) {
        setDrawBounds(clipSquare(bounds));
    }

    /*@Override
    public ValueAnimator createAnimator() {
        float fractions[] = new float[]{0f, 0.5f, 1f};
        return new SpriteAnimatorBuilder(this).
                rotateX(fractions, 0, -180, -180).
                rotateY(fractions, 0, 0, -180).
                duration(1200).
                easeInOut(fractions)
                .build();
    }*/
}
