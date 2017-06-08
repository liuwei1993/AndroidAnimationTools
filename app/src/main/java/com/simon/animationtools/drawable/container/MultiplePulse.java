package com.simon.animationtools.drawable.container;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.shape.Pulse;

/**
 * MultiplePulse
 * Created by simon on 17-6-8.
 */

public class MultiplePulse extends AnimDrawableContainer {

    @NonNull
    @Override
    public Drawable[] createChildren() {
        Pulse[] children = {
                new Pulse(),
                new Pulse(),
                new Pulse(),
        };
        for (int i = 0; i < children.length; i++) {
            children[i].setAnimationDelay(200 * (i + 1));
        }
        return children;
    }

}
