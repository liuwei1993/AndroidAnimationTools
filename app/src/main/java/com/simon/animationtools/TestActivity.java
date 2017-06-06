package com.simon.animationtools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.simon.animationtools.drawable.impl.RotatingPlane;

/**
 * Created by simon on 17-6-6.
 */

public class TestActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ImageView imageView = new ImageView(this);
            RotatingPlane rotatingPlane = new RotatingPlane();
            rotatingPlane.setColor(Color.RED);
            rotatingPlane.setAlpha(255);
            imageView.setImageDrawable(rotatingPlane);
//            setContentView(new ConcatMatrixView(this));
            setContentView(imageView);
        }
    }
    class ConcatMatrixView extends View {
        private Matrix matrix = new Matrix();
        private Paint bgPaint = new Paint();

        public ConcatMatrixView(Context context) {
            super(context);
            matrix.setTranslate(100,100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            bgPaint.setColor(Color.RED);
            canvas.drawRect(0, 0, 100, 100, bgPaint);

            canvas.save();
//            canvas.concat(matrix);
            bgPaint.setColor(Color.BLUE);
            canvas.drawRect(100, 100, 200, 200, bgPaint);
            canvas.restore();

            bgPaint.setColor(Color.BLACK);
            canvas.drawRect(400, 400, 500, 500, bgPaint);
        }


}
