package com.simon.core.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.simon.core.animationtools.drawable.AnimDrawableContainer;
import com.simon.core.animationtools.drawable.shape.CircleAnimDrawable;
import com.simon.core.animationtools.animator.DrawableAnimatorBuilder;

import static com.simon.core.animationtools.utils.ShapeUtils.clipSquare;

/**
 * ThreeBounce
 * Created by simon on 17-6-8.
 */

public class ThreeBounce extends AnimDrawableContainer{

    @NonNull
    @Override
    public Drawable[] createChildren() {
        Bounce[] bounces = {
                new Bounce(),
                new Bounce(),
                new Bounce()
        };
        bounces[1].setAnimationDelay(160);
        bounces[2].setAnimationDelay(320);
        return bounces;
    }

    @Override
    public Bounce getChildAt(int index) {
        return ((Bounce) super.getChildAt(index));
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int radius = bounds.width() / 6;
        int top = bounds.centerY() - radius;
        int bottom = bounds.centerY() + radius;

        for (int i = 0; i < getChildCount(); i++) {
            int center = bounds.width() * (i + 1) / 4
                    + bounds.left;
            getChildAt(i).setDrawBounds(
                    center - radius, top, center + radius, bottom
            );
        }
    }

    private class Bounce extends CircleAnimDrawable {

        Bounce() {
            setScale(0f);
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.4f, 0.8f, 1f};
            return new DrawableAnimatorBuilder(this).scale(fractions, 0f, 1f, 0f, 0f).
                    duration(1400).
                    easeInOut(fractions)
                    .build();
        }
    }
}
