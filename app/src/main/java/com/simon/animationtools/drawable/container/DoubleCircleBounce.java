package com.simon.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.AnimationDrawable;
import com.simon.animationtools.drawable.shape.CircleAnimDrawable;
import com.simon.animationtools.utils.DrawableAnimatorBuilder;

/**
 * Created by simon on 17-6-7.
 */

public class DoubleCircleBounce extends AnimDrawableContainer {

    @NonNull
    @Override
    public AnimationDrawable[] createChildren() {
        AnimationDrawable[] children = new AnimationDrawable[]{new Bounce(Color.BLUE), new Bounce(Color.BLUE)};
        children[1].setAnimationDelay(-1000);
        return children;
    }

    private static class Bounce extends CircleAnimDrawable {

        private Bounce(int color) {
            setAlpha(153);
            setScale(0f);
            setColor(color);
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new DrawableAnimatorBuilder(this).scale(fractions, 0f, 1f, 0f).
                    duration(2000).
                    easeInOut(fractions)
                    .build();
        }
    }

}
