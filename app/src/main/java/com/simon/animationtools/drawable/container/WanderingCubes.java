package com.simon.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.AnimationDrawable;
import com.simon.animationtools.drawable.shape.RectAnimDrawable;
import com.simon.animationtools.utils.AnimatorBuilder;
import com.simon.animationtools.utils.DrawableAnimatorBuilder;

import static com.simon.animationtools.utils.ShapeUtils.clipSquare;

/**
 * Wandering cubes
 * Created by simon on 17-6-8.
 */

public class WanderingCubes extends AnimDrawableContainer {


    @NonNull
    @Override
    public Drawable[] createChildren() {
        AnimationDrawable[] children = {
                new Cube(0),
                new Cube(3)
        };
        children[1].setColor(Color.RED);
        children[0].setColor(Color.BLUE);
        return children;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        for (int i = 0; i < getChildCount(); i++) {
            Drawable child = getChildAt(i);
            ((AnimationDrawable) child).setDrawBounds(
                    bounds.left,
                    bounds.top,
                    bounds.left + bounds.width() / 4,
                    bounds.top + bounds.height() / 4
            );
        }
    }

    private class Cube extends RectAnimDrawable {
        int startFrame;

        public Cube(int startFrame) {
            super();
            this.startFrame = startFrame;
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.25f, 0.5f, 0.51f, 0.75f, 1f};
            AnimatorBuilder builder = new DrawableAnimatorBuilder(this)
                    .rotate(fractions, 0f, -90f, -179f, -180f, -270f, -360f)
                    .translateXPercentage(fractions, 0f, 0.75f, 0.75f, 0.75f, 0f, 0f)
                    .translateYPercentage(fractions, 0f, 0f, 0.75f, 0.75f, 0.75f, 0f)
                    .scale(fractions, 1f, 0.5f, 1f, 1f, 0.5f, 1f)
                    .duration(1800)
                    .easeInOut(fractions)
                    .startFrame(startFrame);
            return builder.build();
        }
    }

}
