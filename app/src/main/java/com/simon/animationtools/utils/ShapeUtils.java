package com.simon.animationtools.utils;

import android.graphics.Rect;

import com.simon.animationtools.drawable.AnimationDrawable;
import com.simon.animationtools.drawable.shape.ShapeAnimDrawable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * utils of shapes
 * Created by simon on 17-6-8.
 */

public class ShapeUtils  {

    public static AnimationDrawable[] createShapeArray(Class<? extends ShapeAnimDrawable> clazz, int count) {
        AnimationDrawable[] array = new AnimationDrawable[count];
        try {
            Constructor<? extends ShapeAnimDrawable> constructor = clazz.getConstructor();
            for (int i = 0; i < array.length; i++) {
                array[i] = constructor.newInstance();
            }
            return array;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("class " + clazz.getName() + " cannot new instance");
    }

    public static Rect clipSquare(Rect rect) {
        int w = rect.width();
        int h = rect.height();
        int min = Math.min(w, h);
        int cx = rect.centerX();
        int cy = rect.centerY();
        int r = min / 2;
        return new Rect(
                cx - r,
                cy - r,
                cx + r,
                cy + r
        );
    }


}
