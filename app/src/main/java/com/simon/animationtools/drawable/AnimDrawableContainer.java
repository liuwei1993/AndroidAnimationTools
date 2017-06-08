package com.simon.animationtools.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
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

public abstract class AnimDrawableContainer extends AnimationDrawable{

    protected final @NonNull Drawable[] children;

    private Paint bgPaint;

    private @ColorInt int bgColor = -1;

    public AnimDrawableContainer() {
        children = createChildren();
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        initCallback();
    }

    public abstract @NonNull Drawable[] createChildren();

    private void initCallback() {
        for (Drawable child : children) {
            child.setCallback(this);
        }
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
        super.draw(canvas);
        drawChildren(canvas);
    }

    @Override
    protected void drawSelf(@NonNull Canvas canvas) {
        if(bgColor != -1) {
            int count = canvas.save();
            bgPaint.setColor(bgColor);
            canvas.drawRect(getBounds(), bgPaint);
            canvas.restoreToCount(count);
        }
    }

    protected void drawChildren(@NonNull Canvas canvas) {
        for (Drawable child : children) {
            int count = canvas.save();
            child.draw(canvas);
            canvas.restoreToCount(count);
        }
    }

    @Override
    public ValueAnimator createAnimator() {
        return null;
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

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        super.start();
        AnimUtils.start(children);
    }

    @Override
    public void stop() {
        super.stop();
        AnimUtils.stop(children);
    }

    @Override
    public boolean isRunning() {
        return AnimUtils.isRunning(children);
    }


}
