package com.simon.animationtools.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.simon.animationtools.utils.AnimUtils;

/**
 *
 * Created by simon on 17-6-7.
 */

public abstract class AnimDrawableContainer extends Drawable implements Drawable.Callback, Animatable{

    protected final @NonNull Drawable[] children;

    private @ColorInt int bgColor = -1;

    private Drawable bgDrawable;

    public AnimDrawableContainer() {
        children = createChildren();
        initCallback();
        onChildrenCreate();
    }

    public abstract @NonNull Drawable[] createChildren();

    private void initCallback() {
        for (Drawable child : children) {
            child.setCallback(this);
        }
    }

    protected void onChildrenCreate() {

    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {

    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if(bgDrawable != null) {
            int count = canvas.save();
            bgDrawable.draw(canvas);
            canvas.restoreToCount(count);
        } else if(bgColor != -1) {
            int count = canvas.save();
            canvas.drawColor(bgColor);
            canvas.restoreToCount(count);
        }
        for (Drawable child : children) {
            int count = canvas.save();
            child.draw(canvas);
            canvas.restoreToCount(count);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        for (Drawable child : children) {
            child.setBounds(bounds);
        }
    }

    public int getChildCount() {
        return children.length;
    }

    public Drawable getChildAt(int index) {
        return children[index];
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        for (Drawable child : children) {
            child.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setBgDrawable(Drawable bgDrawable) {
        this.bgDrawable = bgDrawable;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        AnimUtils.start(children);
    }

    @Override
    public void stop() {
        AnimUtils.stop(children);
    }

    @Override
    public boolean isRunning() {
        return AnimUtils.isRunning(children);
    }


}
