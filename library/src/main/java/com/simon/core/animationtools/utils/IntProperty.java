package com.simon.core.animationtools.utils;

import android.util.Property;

/**
 * Created by simon on 17-6-2.
 */

public abstract class IntProperty<T> extends Property<T, Integer> {

    public IntProperty(String name) {
        super(Integer.class, name);
    }

    /**
     * A type-specific variant of {@link #set(Object, Integer)} that is faster when dealing
     * with fields of type <code>int</code>.
     */
    public abstract void setValue(T object, int value);

    @Override
    final public void set(T object, Integer value) {
        setValue(object, value.intValue());
    }

}