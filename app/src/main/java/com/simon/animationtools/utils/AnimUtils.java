package com.simon.animationtools.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;

import com.simon.animationtools.drawable.AnimationDrawable;


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

    public static void start(AnimationDrawable... animList) {
        for (Animatable animatable : animList) {
            animatable.start();
        }
    }

    public static void stop(AnimationDrawable... animList) {
        for (Animatable animatable : animList) {
            animatable.stop();
        }
    }

    public static boolean isRunning(AnimationDrawable... animList) {
        for (Animatable animatable : animList) {
            if (animatable.isRunning()) {
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
