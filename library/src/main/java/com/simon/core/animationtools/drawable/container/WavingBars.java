package com.simon.core.animationtools.drawable.container;


import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;

import com.simon.core.animationtools.drawable.AnimDrawableContainer;
import com.simon.core.animationtools.drawable.AnimationDrawable;
import com.simon.core.animationtools.drawable.shape.RectAnimDrawable;
import com.simon.core.animationtools.animator.DrawableAnimatorBuilder;
import com.simon.core.animationtools.utils.ShapeUtils;

import static com.simon.core.animationtools.utils.ShapeUtils.clipSquare;

/**
 * wandering bars
 * Created by simon on 17-6-8.
 */

public class WavingBars extends AnimDrawableContainer {

    @NonNull
    @Override
    public AnimationDrawable[] createChildren() {
        AnimationDrawable[] children = ShapeUtils.createShapeArray(WavingItem.class, 5);
        for (int i = 0; i < children.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                children[i].setAnimationDelay(600 + i * 100);
            } else {
                children[i].setAnimationDelay(-1200 + i * 100);
            }
        }
        return children;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int rw = bounds.width() / getChildCount();
        int width = bounds.width() / 5 * 3 / 5;
        for (int i = 0; i < getChildCount(); i++) {
            Drawable child = getChildAt(i);
            int l = bounds.left + i * rw + rw / 5;
            int r = l + width;
            ((AnimationDrawable) child).setDrawBounds(l, bounds.top, r, bounds.bottom);
        }
    }

    public static class WavingItem extends RectAnimDrawable {

        WavingItem() {
            setScaleY(0.4f);
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.2f, 0.4f, 1f};
            return new DrawableAnimatorBuilder(this).scaleY(fractions, 0.4f, 1f, 0.4f, 0.4f)
                    .duration(1200)
                    .easeInOut(fractions)
                    .build();
        }
    }
}
