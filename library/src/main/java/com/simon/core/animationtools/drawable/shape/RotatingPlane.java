package com.simon.core.animationtools.drawable.shape;

import android.animation.ValueAnimator;
import android.graphics.Rect;

import com.simon.core.animationtools.utils.DrawableAnimatorBuilder;

import static com.simon.core.animationtools.utils.ShapeUtils.clipSquare;

public class RotatingPlane extends RectAnimDrawable {

    @Override
    protected void onBoundsChange(Rect bounds) {
        setDrawBounds(clipSquare(bounds));
    }

    @Override
    public ValueAnimator createAnimator() {
        float fractions[] = new float[]{0f, 0.5f, 1f};
        return new DrawableAnimatorBuilder(this).
                rotateX(fractions, 0f, -180f, -180f).
                rotateY(fractions, 0f, 0f, -180f).
                duration(1200).
                easeInOut(fractions)
                .build();
    }
}
