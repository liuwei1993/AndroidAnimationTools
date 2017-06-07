package com.simon.animationtools.drawable.container;

import android.support.annotation.NonNull;

import com.simon.animationtools.drawable.AnimDrawableContainer;
import com.simon.animationtools.drawable.AnimationDrawable;
import com.simon.animationtools.drawable.shape.RotatingPlane;

/**
 * Created by simon on 17-6-7.
 */

public class TestContainer extends AnimDrawableContainer {
    @NonNull
    @Override
    public AnimationDrawable[] createChildren() {
        return new AnimationDrawable[]{new RotatingPlane()};
    }
}
