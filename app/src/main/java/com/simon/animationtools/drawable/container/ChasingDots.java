package com.simon.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.AnimationDrawable;
import com.simon.animationtools.drawable.shape.CircleAnimDrawable;
import com.simon.animationtools.utils.DrawableAnimatorBuilder;

import static com.simon.animationtools.utils.ShapeUtils.clipSquare;

/**
 * ChasingDots
 * Created by simon on 17-6-8.
 */

public class ChasingDots extends AnimDrawableContainer {

    @NonNull
    @Override
    public Drawable[] createChildren() {
        Dot[] dots = {
                new Dot(),
                new Dot()
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dots[1].setAnimationDelay(1000);
        } else {
            dots[1].setAnimationDelay(-1000);
        }
        return dots;
    }

    @Override
    public ValueAnimator createAnimator() {
        float fractions[] = new float[]{0f, 1f};
        return new DrawableAnimatorBuilder(this).
                rotate(fractions, 0f, 360f).
                duration(2000).
                interpolator(new LinearInterpolator()).
                build();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int drawW = (int) (bounds.width() * 0.6f);
        ((AnimationDrawable) getChildAt(0)).setDrawBounds(
                bounds.right - drawW,
                bounds.top,
                bounds.right
                , bounds.top + drawW
        );
        ((AnimationDrawable) getChildAt(1)).setDrawBounds(
                bounds.right - drawW,
                bounds.bottom - drawW,
                bounds.right,
                bounds.bottom
        );
    }

    private class Dot extends CircleAnimDrawable {

        Dot() {
            setScale(0f);
            setAlpha(170);
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new DrawableAnimatorBuilder(this).
                    scale(fractions, 0f, 1f, 0f).
                    duration(2000).
                    easeInOut(fractions)
                    .build();
        }
    }

}
