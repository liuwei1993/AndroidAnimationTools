package com.simon.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.shape.CircleAnimDrawable;
import com.simon.animationtools.utils.DrawableAnimatorBuilder;

import static com.simon.animationtools.utils.ShapeUtils.clipSquare;

/**
 *
 * Created by simon on 17-6-8.
 */

public class RotatingDots2 extends AnimDrawableContainer {


    @Override
    public void drawChildren(@NonNull Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            Dot dot = getChildAt(i);
            int count = canvas.save();
            canvas.rotate(i * 360 / getChildCount(),
                    getBounds().centerX(),
                    getBounds().centerY());
            dot.draw(canvas);
            canvas.restoreToCount(count);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int radius = (int) (bounds.width() * Math.PI / 3f / getChildCount());
        int left = bounds.centerX() - radius;
        int right = bounds.centerX() + radius;
        for (int i = 0; i < getChildCount(); i++) {
            Dot dot = getChildAt(i);
            dot.setDrawBounds(left, bounds.top, right, bounds.top + radius * 2);
        }
    }

    @Override
    public Dot getChildAt(int index) {
        return ((Dot) super.getChildAt(index));
    }

    @NonNull
    @Override
    public Drawable[] createChildren() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].setAnimationDelay(1200 / 12 * i);
            } else {
                dots[i].setAnimationDelay(1200 / 12 * i + -1200);
            }
        }
        return dots;
    }


    private class Dot extends CircleAnimDrawable {

        Dot() {
            setAlpha(0);
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.39f, 0.4f, 1f};
            return new DrawableAnimatorBuilder(this)
                    .alpha(fractions, 0f, 0f, 1f, 0f)
                    .duration(1200)
                    .easeInOut(fractions)
                    .build();
        }
    }


}
