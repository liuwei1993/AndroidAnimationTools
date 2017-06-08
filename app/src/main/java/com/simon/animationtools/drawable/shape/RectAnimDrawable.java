package com.simon.animationtools.drawable.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * abstract class ofRect animation drawable
 * Created by simon on 17-6-6.
 */

public abstract class RectAnimDrawable extends ShapeAnimDrawable {
    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        canvas.drawRect(getDrawBounds(),paint);
    }

}
