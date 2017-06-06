package com.simon.animationtools.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by simon on 17-6-6.
 */

public abstract class RectAnimDrawable extends ShapeAnimDrawable {
    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        Log.e("test","bounds : " + getDrawBounds());
        canvas.drawRect(getDrawBounds(),paint);
    }

    @Override
    protected ValueAnimator createAnimator() {
        return null;
    }
}
