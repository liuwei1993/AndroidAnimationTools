package com.simon.core.animationtools.animator.interpolator;

import android.view.animation.Interpolator;

/**
 * Created by simon liu.
 */
public class Ease {
    public static Interpolator inOut() {
        return PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f);
    }
}
