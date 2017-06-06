package com.simon.animationtools.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;


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

    public static void start(Animatable... animList) {
        for (Animatable animatable : animList) {
            animatable.start();
        }
    }

    public static void stop(Animatable... animList) {
        for (Animatable animatable : animList) {
            animatable.stop();
        }
    }

    public static boolean isRunning(Animatable... animList) {
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
