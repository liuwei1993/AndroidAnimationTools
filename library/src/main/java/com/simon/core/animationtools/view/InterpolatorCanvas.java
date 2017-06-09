package com.simon.core.animationtools.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by simon on 17-5-12.
 */

public class InterpolatorCanvas extends View {

    private Interpolator interpolator;

    private Paint paint;

    public InterpolatorCanvas(Context context) {
        super(context);
        init();
    }

    public InterpolatorCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InterpolatorCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        paint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, width, height, paint);
        if (interpolator == null) return;
        paint.setColor(Color.BLUE);
        for (int i = 0; i < width; i++) {
            float x = (((float) i) / width);
            float y = interpolator.getInterpolation(x);
            canvas.drawPoint(x * width, (1 - y) * height, paint);
        }
    }
}
