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
        if (getBounds() != null) {
            int radius = Math.min(getBounds().width(), getBounds().height()) / 2;
            canvas.drawCircle(getBounds().centerX(),
                    getBounds().centerY(),
                    radius, paint);
        }
    }

}
