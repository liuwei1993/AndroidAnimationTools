package com.simon.core.animationtools.animator;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Log;
import android.util.Property;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.simon.core.animationtools.animator.interpolator.KeyFrameInterpolator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * 
 * Created by simon on 17-6-7.
 */

public abstract class AnimatorBuilder {


    private static final String TAG = "AnimatorBuilder";
    private Object target;
    private Interpolator interpolator;
    private int repeatCount = Animation.INFINITE;
    private long duration = 2000;
    private int startFrame = 0;
    private Map<String, FrameData> fds = new HashMap<>();


    class FrameData<T> {
        FrameData(float[] fractions, Property property, T[] values) {
            this.fractions = fractions;
            this.property = property;
            this.values = values;
        }

        float[] fractions;
        Property property;
        T[] values;
    }

    private class IntFrameData extends FrameData<Integer> {

        IntFrameData(float[] fractions, Property property, Integer[] values) {
            super(fractions, property, values);
        }
    }

    private class FloatFrameData extends FrameData<Float> {

        FloatFrameData(float[] fractions, Property property, Float[] values) {
            super(fractions, property, values);
        }
    }

    public AnimatorBuilder(Object target) {
        this.target = target;
    }

    protected abstract Property getScaleProperty();

    public AnimatorBuilder scale(float fractions[], Float... scale) {
        holder(fractions, getScaleProperty(), scale);
        return this;
    }
    protected abstract Property getAlphaProperty();


    public AnimatorBuilder alpha(float fractions[], Float... alpha) {
        holder(fractions, getAlphaProperty(), alpha);
        return this;
    }

    protected abstract Property getScaleXProperty();

    @SuppressWarnings("unused")
    public AnimatorBuilder scaleX(float fractions[], Float... scaleX) {
        holder(fractions, getScaleXProperty(), scaleX);
        return this;
    }

    protected abstract Property getScaleYProperty();

    public AnimatorBuilder scaleY(float fractions[], Float... scaleY) {
        holder(fractions, getScaleYProperty(), scaleY);
        return this;
    }

    protected abstract Property getRotateXProperty();

    public AnimatorBuilder rotateX(float fractions[], Float... rotateX) {
        holder(fractions, getRotateXProperty(), rotateX);
        return this;
    }

    protected abstract Property getRotateYProperty();

    public AnimatorBuilder rotateY(float fractions[], Float... rotateY) {
        holder(fractions, getRotateYProperty(), rotateY);
        return this;
    }

    protected abstract Property getTranslateXProperty();

    @SuppressWarnings("unused")
    public AnimatorBuilder translateX(float fractions[], Float... translateX) {
        holder(fractions, getTranslateXProperty(), translateX);
        return this;
    }

    protected abstract Property getTranslateYProperty();

    @SuppressWarnings("unused")
    public AnimatorBuilder translateY(float fractions[], Float... translateY) {
        holder(fractions, getTranslateYProperty(), translateY);
        return this;
    }

    protected abstract Property getRotateProperty();

    public AnimatorBuilder rotate(float fractions[], Float... rotate) {
        holder(fractions, getRotateProperty(), rotate);
        return this;
    }

    protected abstract Property getTranslateXPercentageProperty();

    public AnimatorBuilder translateXPercentage(float fractions[], Float... translateXPercentage) {
        holder(fractions, getTranslateXPercentageProperty(), translateXPercentage);
        return this;
    }

    protected abstract Property getTranslateYPercentageProperty();

    public AnimatorBuilder translateYPercentage(float[] fractions, Float... translateYPercentage) {
        holder(fractions, getTranslateYPercentageProperty(), translateYPercentage);
        return this;
    }

    private void holder(float[] fractions, Property property, Float[] values) {
        ensurePair(fractions.length, values.length);
        fds.put(property.getName(), new FloatFrameData(fractions, property, values));
    }


    private void holder(float[] fractions, Property property, Integer[] values) {
        ensurePair(fractions.length, values.length);
        fds.put(property.getName(), new IntFrameData(fractions, property, values));
    }

    private void ensurePair(int fractionsLength, int valuesLength) {
        if (fractionsLength != valuesLength) {
            throw new IllegalStateException(String.format(
                    Locale.getDefault(),
                    "The fractions.length must equal values.length, " +
                            "fraction.length[%d], values.length[%d]",
                    fractionsLength,
                    valuesLength));
        }
    }


    public AnimatorBuilder interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public AnimatorBuilder easeInOut(float... fractions) {
        interpolator(KeyFrameInterpolator.easeInOut(
                fractions
        ));
        return this;
    }


    public AnimatorBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    @SuppressWarnings("unused")
    public AnimatorBuilder repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public AnimatorBuilder startFrame(int startFrame) {
        if (startFrame < 0) {
            Log.w(TAG, "startFrame should always be non-negative");
            startFrame = 0;
        }
        this.startFrame = startFrame;
        return this;
    }

    public ObjectAnimator build() {

        PropertyValuesHolder[] holders = new PropertyValuesHolder[fds.size()];
        int i = 0;
        for (Map.Entry<String, FrameData> fd : fds.entrySet()) {
            FrameData data = fd.getValue();
            Keyframe[] keyframes = new Keyframe[data.fractions.length];
            float[] fractions = data.fractions;
            float startF = fractions[startFrame];
            for (int j = startFrame; j < (startFrame + data.values.length); j++) {
                int key = j - startFrame;
                int vk = j % data.values.length;
                float fraction = fractions[vk] - startF;
                if (fraction < 0) {
                    fraction = fractions[fractions.length - 1] + fraction;
                }
                if (data instanceof IntFrameData) {
                    keyframes[key] = Keyframe.ofInt(fraction, (Integer) data.values[vk]);
                } else if (data instanceof FloatFrameData) {
                    keyframes[key] = Keyframe.ofFloat(fraction, (Float) data.values[vk]);
                } else {
                    keyframes[key] = Keyframe.ofObject(fraction, data.values[vk]);
                }
            }
            holders[i] = PropertyValuesHolder.ofKeyframe(data.property, keyframes);
            i++;
        }

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target,
                holders);
        animator.setDuration(duration);
        animator.setRepeatCount(repeatCount);
        animator.setInterpolator(interpolator);
        return animator;
    }
    
}
