package com.simon.animationtools.drawable.container;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.shape.RectAnimDrawable;
import com.simon.animationtools.utils.DrawableAnimatorBuilder;

import static com.simon.animationtools.utils.ShapeUtils.clipSquare;

/**
 * Created by simon on 17-6-8.
 */

public class CubeGrid extends AnimDrawableContainer {

    @NonNull
    @Override
    public Drawable[] createChildren() {
        int delays[] = new int[]{
                200, 300, 400
                , 100, 200, 300
                , 0, 100, 200
        };
        GridItem[] gridItems = new GridItem[9];
        for (int i = 0; i < gridItems.length; i++) {
            gridItems[i] = new GridItem();
            gridItems[i].setAnimationDelay(delays[i]);
        }
        return gridItems;
    }

    @Override
    public GridItem getChildAt(int index) {
        return ((GridItem) super.getChildAt(index));
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int width = (int) (bounds.width() * 0.33f);
        int height = (int) (bounds.height() * 0.33f);
        for (int i = 0; i < getChildCount(); i++) {
            int x = i % 3;
            int y = i / 3;
            int l = bounds.left + x * width;
            int t = bounds.top + y * height;
            GridItem sprite = getChildAt(i);
            sprite.setDrawBounds(l, t, l + width, t + height);
        }
    }

    private class GridItem extends RectAnimDrawable {
        @Override
        public ValueAnimator createAnimator() {
            float fractions[] = new float[]{0f, 0.35f, 0.7f, 1f};
            return new DrawableAnimatorBuilder(this).
                    scale(fractions, 1f, 0f, 1f, 1f).
                    duration(1300).
                    easeInOut(fractions)
                    .build();
        }
    }



}
