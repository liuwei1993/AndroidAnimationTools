package com.simon.core.animationtools.utils;

import android.support.annotation.FloatRange;
import android.util.Property;

import com.simon.core.animationtools.drawable.AnimationDrawable;

/**
 * 
 * Created by simon on 17-6-7.
 */

public class DrawableAnimatorBuilder extends AnimatorBuilder {

    public DrawableAnimatorBuilder(AnimationDrawable target) {
        super(target);
    }

    public static final Property<AnimationDrawable,Float> ROTATE_X = new FloatProperty<AnimationDrawable>("rotateX") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setRotationX(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getRotationX();
        }
    };

    public static final Property<AnimationDrawable, Float> ROTATE = new FloatProperty<AnimationDrawable>("rotate") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setRotation(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getRotation();
        }
    };

    public static final Property<AnimationDrawable, Float> ROTATE_Y = new FloatProperty<AnimationDrawable>("rotateY") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setRotationY((int) value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getRotationY();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<AnimationDrawable, Float> TRANSLATE_X = new FloatProperty<AnimationDrawable>("translateX") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setTranslationX(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getTranslationX();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<AnimationDrawable, Float> TRANSLATE_Y = new FloatProperty<AnimationDrawable>("translateY") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setTranslationY(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getTranslationY();
        }
    };

    public static final Property<AnimationDrawable, Float> TRANSLATE_X_PERCENTAGE = new FloatProperty<AnimationDrawable>("translateXPercentage") {
        @Override
        public void setValue(AnimationDrawable target, @FloatRange(from = 0f,to = 1f) float value) {
            target.setTranslationXPercentage(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getTranslationXPercentage();
        }
    };

    public static final Property<AnimationDrawable, Float> TRANSLATE_Y_PERCENTAGE = new FloatProperty<AnimationDrawable>("translateYPercentage") {

        @Override
        public void setValue(AnimationDrawable target, @FloatRange(from = 0f,to = 1f) float value) {
            target.setTranslationYPercentage(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getTranslationYPercentage();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<AnimationDrawable, Float> SCALE_X = new FloatProperty<AnimationDrawable>("scaleX") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setScaleX(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getScaleX();
        }
    };

    public static final Property<AnimationDrawable, Float> SCALE_Y = new FloatProperty<AnimationDrawable>("scaleY") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setScaleY(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getScaleY();
        }
    };

    public static final Property<AnimationDrawable, Float> SCALE = new FloatProperty<AnimationDrawable>("scale") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setScaleX(value);
            target.setScaleY(value);
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getScaleX();
        }
    };

    public static final Property<AnimationDrawable, Float> ALPHA = new FloatProperty<AnimationDrawable>("alpha") {
        @Override
        public void setValue(AnimationDrawable target, float value) {
            target.setAlpha((int) (value * 255));
        }

        @Override
        public Float get(AnimationDrawable target) {
            return target.getAlpha() / 255f;
        }
    };

    @Override
    protected Property getScaleProperty() {
        return SCALE;
    }

    @Override
    protected Property getAlphaProperty() {
        return ALPHA;
    }

    @Override
    protected Property getScaleXProperty() {
        return SCALE_X;
    }

    @Override
    protected Property getScaleYProperty() {
        return SCALE_Y;
    }

    @Override
    protected Property getRotateXProperty() {
        return ROTATE_X;
    }

    @Override
    protected Property getRotateYProperty() {
        return ROTATE_Y;
    }

    @Override
    protected Property getTranslateXProperty() {
        return TRANSLATE_X;
    }

    @Override
    protected Property getTranslateYProperty() {
        return TRANSLATE_Y;
    }

    @Override
    protected Property getRotateProperty() {
        return ROTATE;
    }

    @Override
    protected Property getTranslateXPercentageProperty() {
        return TRANSLATE_X_PERCENTAGE;
    }

    @Override
    protected Property getTranslateYPercentageProperty() {
        return TRANSLATE_Y_PERCENTAGE;
    }
    
    
}
