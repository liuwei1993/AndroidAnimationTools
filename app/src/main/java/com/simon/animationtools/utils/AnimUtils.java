package com.simon.animationtools.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;


public class AnimUtils {

    public static void start(Animator animator) {
        if (animator != null && !animator.isStarted()) {
            animator.start();
        }
    }

    public static void stop(Animator animator) {
        if (animator != null && !animator.isRunning()) {
            animator.end();
        }
    }

    public static void start(Drawable... animList) {
        for (Drawable drawable : animList) {
            if(drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        }
    }

    public static void stop(Drawable... animList) {
        for (Drawable drawable : animList) {
            if(drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            }
        }
    }

    public static boolean isRunning(Drawable... animList) {
        for (Drawable drawable : animList) {
            if (drawable instanceof Animatable && ((Animatable) drawable).isRunning()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRunning(ValueAnimator animator) {
        return animator != null && animator.isRunning();
    }

    public static boolean isStarted(ValueAnimator animator) {
        return animator != null && animator.isStarted();
    }
}
