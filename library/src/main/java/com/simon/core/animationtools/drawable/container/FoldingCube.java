package com.simon.core.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

import com.simon.core.animationtools.drawable.AnimDrawableContainer;
import com.simon.core.animationtools.drawable.shape.RectAnimDrawable;
import com.simon.core.animationtools.animator.DrawableAnimatorBuilder;

import static com.simon.core.animationtools.utils.ShapeUtils.clipSquare;

/**
 * FoldingCube
 * Created by simon on 17-6-8.
 */

public class FoldingCube extends AnimDrawableContainer {

    @NonNull
    @Override
    public Drawable[] createChildren() {
        Cube[] cubes
                = new Cube[4];
        for (int i = 0; i < cubes.length; i++) {
            cubes[i] = new Cube();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cubes[i].setAnimationDelay(300 * i);
            } else {
                cubes[i].setAnimationDelay(300 * i - 1200);
            }
        }
        return cubes;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int size = Math.min(bounds.width(), bounds.height());
        size = (int) Math.sqrt(
                (size
                        * size) / 2);
        int oW = (bounds.width() - size) / 2;
        int oH = (bounds.height() - size) / 2;
        bounds = new Rect(
                bounds.left + oW,
                bounds.top + oH,
                bounds.right - oW,
                bounds.bottom - oH
        );
        int px = bounds.left + size / 2 + 1;
        int py = bounds.top + size / 2 + 1;
        for (int i = 0; i < getChildCount(); i++) {
            Cube cube = getChildAt(i);
            cube.setDrawBounds(
                    bounds.left,
                    bounds.top,
                    px,
                    py
            );
            cube.setPivotX(cube.getDrawBounds().right);
            cube.setPivotY(cube.getDrawBounds().bottom);
        }
    }

    @Override
    public Cube getChildAt(int index) {
        return ((Cube) super.getChildAt(index));
    }

    @Override
    public void drawChildren(@NonNull Canvas canvas) {

        Rect bounds = clipSquare(getBounds());
        for (int i = 0; i < getChildCount(); i++) {
            int count = canvas.save();
            canvas.rotate(45 + i * 90, bounds.centerX(), bounds.centerY());
            Cube cube = getChildAt(i);
            cube.draw(canvas);
            canvas.restoreToCount(count);
        }
    }

    private class Cube extends RectAnimDrawable {

        Cube() {
            setAlpha(0);
            setRotationX(-180);
        }

        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.1f, 0.25f, 0.75f, 0.9f, 1f};
            return new DrawableAnimatorBuilder(this)
                    .alpha(fractions, 0f, 0f, 1f, 1f, 0f, 0f)
                    .rotateX(fractions, -180f, -180f, 0f, 0f, 0f, 0f)
                    .rotateY(fractions, 0f, 0f, 0f, 0f, 180f, 180f)
                    .duration(2400)
                    .interpolator(new LinearInterpolator())
                    .build();
        }
    }


}
