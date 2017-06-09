package com.simon.core.animationtools.animator;

import android.support.annotation.FloatRange;
import android.util.Property;
import android.view.View;

/**
 * Created by simon liu.
 */
public class ViewAnimatorBuilder extends AnimatorBuilder {

    public ViewAnimatorBuilder(View target) {
        super(target);
    }

    public static final Property<View,Float> ROTATE_X = new FloatProperty<View>("rotateX") {
        @Override
        public void setValue(View target, float value) {
            target.setRotationX(value);
        }

        @Override
        public Float get(View target) {
            return target.getRotationX();
        }
    };

    public static final Property<View, Float> ROTATE = new FloatProperty<View>("rotate") {
        @Override
        public void setValue(View target, float value) {
            target.setRotation(value);
        }

        @Override
        public Float get(View target) {
            return target.getRotation();
        }
    };

    public static final Property<View, Float> ROTATE_Y = new FloatProperty<View>("rotateY") {
        @Override
        public void setValue(View target, float value) {
            target.setRotationY(value);
        }

        @Override
        public Float get(View target) {
            return target.getRotationY();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<View, Float> TRANSLATE_X = new FloatProperty<View>("translateX") {
        @Override
        public void setValue(View target, float value) {
            target.setTranslationX(value);
        }

        @Override
        public Float get(View target) {
            return target.getTranslationX();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<View, Float> TRANSLATE_Y = new FloatProperty<View>("translateY") {
        @Override
        public void setValue(View target, float value) {
            target.setTranslationY(value);
        }

        @Override
        public Float get(View target) {
            return target.getTranslationY();
        }
    };

    public static final Property<View, Float> TRANSLATE_X_PERCENTAGE = new FloatProperty<View>("translateXPercentage") {
        @Override
        public void setValue(View target, @FloatRange(from = 0f,to = 1f) float value) {
            int width = target.getWidth();
            target.setTranslationX(value * width);
        }

        @Override
        public Float get(View target) {
            float translationX = target.getTranslationX();
            return translationX / target.getWidth();
        }
    };

    public static final Property<View, Float> TRANSLATE_Y_PERCENTAGE = new FloatProperty<View>("translateYPercentage") {

        @Override
        public void setValue(View target, @FloatRange(from = 0f,to = 1f) float value) {
            int width = target.getHeight();
            target.setTranslationY(value * width);
        }

        @Override
        public Float get(View target) {
            return target.getTranslationY() / target.getHeight();
        }
    };

    @SuppressWarnings("unused")
    public static final Property<View, Float> SCALE_X = new FloatProperty<View>("scaleX") {
        @Override
        public void setValue(View target, float value) {
            target.setScaleX(value);
        }

        @Override
        public Float get(View target) {
            return target.getScaleX();
        }
    };

    public static final Property<View, Float> SCALE_Y = new FloatProperty<View>("scaleY") {
        @Override
        public void setValue(View target, float value) {
            target.setScaleY(value);
        }

        @Override
        public Float get(View target) {
            return target.getScaleY();
        }
    };

    public static final Property<View, Float> SCALE = new FloatProperty<View>("scale") {
        @Override
        public void setValue(View target, float value) {
            target.setScaleX(value);
            target.setScaleY(value);
        }

        @Override
        public Float get(View target) {
            return target.getScaleX();
        }
    };

    public static final Property<View, Float> ALPHA = new FloatProperty<View>("alpha") {
        @Override
        public void setValue(View target, float value) {
            target.setAlpha(value);
        }

        @Override
        public Float get(View target) {
            return target.getAlpha();
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
