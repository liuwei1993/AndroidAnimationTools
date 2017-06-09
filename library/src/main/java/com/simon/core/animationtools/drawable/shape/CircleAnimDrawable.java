package com.simon.core.animationtools.drawable.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * CircleAnimDrawable
 * Created by simon on 17-6-7.
 */

public abstract class CircleAnimDrawable extends ShapeAnimDrawable {

    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        Rect drawBounds = getDrawBounds();
        int radius = Math.min(drawBounds.width() / 2, drawBounds.height()) / 2;
        canvas.drawCircle(drawBounds.centerX(),
                drawBounds.centerY(),
                radius, paint);
    }

}
