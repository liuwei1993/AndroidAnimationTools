package com.simon.animationtools.drawable;

import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.simon.animationtools.utils.AnimUtils;

/**
 *
 * Created by simon on 17-6-5.
 */

public abstract class AnimationDrawable extends Drawable implements
        ValueAnimator.AnimatorUpdateListener,
        Animatable, Drawable.Callback {

    private static final Rect ZERO_BOUNDS_RECT = new Rect();

    private ValueAnimator animator;

    private Camera mCamera;

    private Matrix mMatrix;

    private float scale = 1;
    private float scaleX = 1;
    private float scaleY = 1;
    private float pivotX;
    private float pivotY;
    private int animationDelay;
    private float rotationX;
    private float rotationY;
    private float translationX;
    private float translationY;
    private float rotation;
    private float translationXPercentage;
    private float translationYPercentage;
    private @IntRange(from = 0, to = 255) int alpha = 255;
    protected Rect drawBounds = ZERO_BOUNDS_RECT;


    @Override
    public int getAlpha() {
        return alpha;
    }

    public AnimationDrawable() {
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    public abstract ValueAnimator createAnimator();

    protected abstract void drawSelf(@NonNull Canvas canvas);

    @Override
    public void draw(@NonNull Canvas canvas) {
        float tx = getTranslationX();
        tx = tx == 0 ? (int) (getBounds().width() * getTranslationXPercentage()) : tx;
        float ty = getTranslationY();
        ty = ty == 0 ? (int) (getBounds().height() * getTranslationYPercentage()) : ty;
        canvas.translate(tx, ty);
        canvas.scale(getScaleX(), getScaleY(), getPivotX(), getPivotY());
        canvas.rotate(getRotation(), getPivotX(), getPivotY());

        if (getRotationX() != 0 || getRotationY() != 0) {
            mCamera.save();
            mCamera.rotateX(getRotationX());
            mCamera.rotateY(getRotationY());
            mCamera.getMatrix(mMatrix);
            mMatrix.preTranslate(-getPivotX(), -getPivotY());
            mMatrix.postTranslate(getPivotX(), getPivotY());
            mCamera.restore();
            canvas.concat(mMatrix);
        }
        drawSelf(canvas);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        this.alpha = alpha;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        //do nothing
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        scheduleSelf(what,when);
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        unscheduleSelf(what);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void reset() {
        scale = 1;
        rotationX = 0;
        rotationY = 0;
        translationX = 0;
        translationY = 0;
        rotation = 0;
        translationXPercentage = 0f;
        translationYPercentage = 0f;
    }

    @Override
    public void start() {
        animator = createAnimator();
        if(animator != null) {
            animator.addUpdateListener(this);
            animator.setStartDelay(getAnimationDelay());
            AnimUtils.start(animator);
        }
    }

    @Override
    public void stop() {
        AnimUtils.stop(animator);
        reset();
        if(animator != null) {
            animator.removeUpdateListener(this);
            animator = null;
        }
    }

    @Override
    public boolean isRunning() {
        return AnimUtils.isRunning(animator);
    }

    //getters and setters


    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getPivotX() {
        return pivotX;
    }

    public float getPivotY() {
        return pivotY;
    }

    public int getAnimationDelay() {
        return animationDelay;
    }

    public void setAnimationDelay(int animationDelay) {
        this.animationDelay = animationDelay;
    }

    public float getRotationX() {
        return rotationX;
    }

    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    public float getRotationY() {
        return rotationY;
    }

    public void setRotationY(float rotateY) {
        this.rotationY = rotateY;
    }

    public float getTranslationX() {
        return translationX;
    }

    public void setTranslationX(float translationX) {
        this.translationX = translationX;
    }

    public float getTranslationY() {
        return translationY;
    }

    public void setTranslationY(float translationY) {
        this.translationY = translationY;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotate) {
        this.rotation = rotate;
    }

    public float getTranslationXPercentage() {
        return translationXPercentage;
    }

    public void setTranslationXPercentage(float translationXPercentage) {
        this.translationXPercentage = translationXPercentage;
    }

    public float getTranslationYPercentage() {
        return translationYPercentage;
    }

    public void setTranslationYPercentage(float translationYPercentage) {
        this.translationYPercentage = translationYPercentage;
    }

    public void setPivotX(float pivotX) {
        this.pivotX = pivotX;
    }

    public void setPivotY(float pivotY) {
        this.pivotY = pivotY;
    }

    public Rect getDrawBounds() {
        return drawBounds;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.setDrawBounds(bounds);
    }

    public void setDrawBounds(@NonNull Rect drawBounds) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom);
    }

    public void setDrawBounds(int left, int top, int right, int bottom) {
        this.drawBounds = new Rect(left, top, right, bottom);
        pivotX = drawBounds.centerX();
        pivotY = drawBounds.centerY();
    }

}
