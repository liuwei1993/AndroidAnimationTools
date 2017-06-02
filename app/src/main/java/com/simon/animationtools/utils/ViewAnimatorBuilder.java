package com.simon.animationtools.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.simon.animationtools.interpolator.KeyFrameInterpolator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by simon liu.
 */
public class ViewAnimatorBuilder {

    private static final String TAG = "ViewAnimatorBuilder";
    private View target;
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

    public ViewAnimatorBuilder(View view) {
        this.target = view;
    }

    public ViewAnimatorBuilder scale(float fractions[], Float... scale) {
        holder(fractions, SCALE, scale);
        return this;
    }

    public ViewAnimatorBuilder alpha(float fractions[], Float... alpha) {
        holder(fractions, ALPHA, alpha);
        return this;
    }

    @SuppressWarnings("unused")
    public ViewAnimatorBuilder scaleX(float fractions[], Float... scaleX) {
        holder(fractions, SCALE, scaleX);
        return this;
    }

    public ViewAnimatorBuilder scaleY(float fractions[], Float... scaleY) {
        holder(fractions, SCALE_Y, scaleY);
        return this;
    }

    public ViewAnimatorBuilder rotateX(float fractions[], Float... rotateX) {
        holder(fractions, ROTATE_X, rotateX);
        return this;
    }

    public ViewAnimatorBuilder rotateY(float fractions[], Float... rotateY) {
        holder(fractions, ROTATE_Y, rotateY);
        return this;
    }

    @SuppressWarnings("unused")
    public ViewAnimatorBuilder translateX(float fractions[], Float... translateX) {
        holder(fractions, TRANSLATE_X, translateX);
        return this;
    }


    @SuppressWarnings("unused")
    public ViewAnimatorBuilder translateY(float fractions[], Float... translateY) {
        holder(fractions, TRANSLATE_Y, translateY);
        return this;
    }


    public ViewAnimatorBuilder rotate(float fractions[], Float... rotate) {
        holder(fractions, ROTATE, rotate);
        return this;
    }

    /*public ViewAnimatorBuilder translateXPercentage(float fractions[], Float... translateXPercentage) {
        holder(fractions, TRANSLATE_X_PERCENTAGE, translateXPercentage);
        return this;
    }

    public ViewAnimatorBuilder translateYPercentage(float[] fractions, Float... translateYPercentage) {
        holder(fractions, TRANSLATE_Y_PERCENTAGE, translateYPercentage);
        return this;
    }*/

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


    public ViewAnimatorBuilder interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public ViewAnimatorBuilder easeInOut(float... fractions) {
        interpolator(KeyFrameInterpolator.easeInOut(
                fractions
        ));
        return this;
    }


    public ViewAnimatorBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    @SuppressWarnings("unused")
    public ViewAnimatorBuilder repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public ViewAnimatorBuilder startFrame(int startFrame) {
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


    public static final Property<View,Float> ROTATE_X = new FloatProperty<View>("rotateX") {
        @Override
        public void setValue(View object, float value) {
            object.setRotationX(value);
        }

        @Override
        public Float get(View object) {
            return object.getRotationX();
        }
    };

    public static final Property<View, Float> ROTATE = new FloatProperty<View>("rotate") {
        @Override
        public void setValue(View object, float value) {
            object.setRotation(value);
        }

        @Override
        public Float get(View object) {
            return object.getRotation();
        }
    };

    public static final Property<View, Float> ROTATE_Y = new FloatProperty<View>("rotateY") {
        @Override
        public void setValue(View object, float value) {
            object.setRotationY(value);
        }

        @Override
        public Float get(View object) {
            return object.getRotationY();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<View, Float> TRANSLATE_X = new FloatProperty<View>("translateX") {
        @Override
        public void setValue(View object, float value) {
            object.setTranslationX(value);
        }

        @Override
        public Float get(View object) {
            return object.getTranslationX();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<View, Float> TRANSLATE_Y = new FloatProperty<View>("translateY") {
        @Override
        public void setValue(View object, float value) {
            object.setTranslationY(value);
        }

        @Override
        public Float get(View object) {
            return object.getTranslationY();
        }
    };

    /*public static final Property<Object, Float> TRANSLATE_X_PERCENTAGE = new FloatProperty<Object>("translateXPercentage") {
        @Override
        public void setValue(Object object, float value) {
            object.setTranslateXPercentage(value);
        }

        @Override
        public Float get(Object object) {
            return object.getTranslateXPercentage();
        }
    };

    public static final Property<Object, Float> TRANSLATE_Y_PERCENTAGE = new FloatProperty<Object>("translateYPercentage") {
        @Override
        public void setValue(Object object, float value) {
            object.setTranslateYPercentage(value);
        }

        @Override
        public Float get(Object object) {
            return object.getTranslateYPercentage();
        }
    };*/

    @SuppressWarnings("unused")
    public static final Property<View, Float> SCALE_X = new FloatProperty<View>("scaleX") {
        @Override
        public void setValue(View object, float value) {
            object.setScaleX(value);
        }

        @Override
        public Float get(View object) {
            return object.getScaleX();
        }
    };

    public static final Property<View, Float> SCALE_Y = new FloatProperty<View>("scaleY") {
        @Override
        public void setValue(View object, float value) {
            object.setScaleY(value);
        }

        @Override
        public Float get(View object) {
            return object.getScaleY();
        }
    };

    public static final Property<View, Float> SCALE = new FloatProperty<View>("scale") {
        @Override
        public void setValue(View object, float value) {
            object.setScaleX(value);
            object.setScaleY(value);
        }

        @Override
        public Float get(View object) {
            return object.getScaleX();
        }
    };

    public static final Property<View, Float> ALPHA = new FloatProperty<View>("alpha") {
        @Override
        public void setValue(View object, float value) {
            object.setAlpha(value);
        }

        @Override
        public Float get(View object) {
            return object.getAlpha();
        }
    };

}
