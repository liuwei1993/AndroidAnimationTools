package com.simon.animationtools.drawable.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *
 * Created by simon on 17-6-7.
 */

public abstract class CircleAnimDrawable extends ShapeAnimDrawable{

    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            int radius = Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2;
            canvas.drawCircle(getDrawBounds().centerX(),
                    getDrawBounds().centerY(),
                    radius, paint);
        }
    }

}
