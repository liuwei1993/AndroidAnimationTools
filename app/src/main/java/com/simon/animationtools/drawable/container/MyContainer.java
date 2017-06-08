package com.simon.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.shape.CircleAnimDrawable;

/**
 * Created by simon on 17-6-8.
 */

public class MyContainer extends AnimDrawableContainer {
    @NonNull
    @Override
    public Drawable[] createChildren() {
        CircleAnimDrawable circleAnimDrawable = new CircleAnimDrawable() {
            @Override
            protected ValueAnimator createAnimator() {
                /*float fractions[] = new float[]{0f, 0.5f, 1f};
                return new DrawableAnimatorBuilder(this).
                        scale(fractions, 0f, 1f, 0f).
                        duration(2000).
                        easeInOut(fractions)
                        .build();*/
                return null;
            }
        };
        circleAnimDrawable.setColor(Color.BLUE);
        return new Drawable[]{circleAnimDrawable};
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        ((CircleAnimDrawable) getChildAt(0)).setDrawBounds(bounds.left + bounds.width() / 2, bounds.top + bounds.height() / 2, bounds.right, bounds.bottom);
    }
}
