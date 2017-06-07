package com.simon.animationtools;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.simon.animationtools.drawable.impl.RotatingPlane;

/**
 * test activity
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
        setContentView(R.layout.activity_test);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        RotatingPlane rotatingPlane = new RotatingPlane();
        rotatingPlane.setColor(Color.RED);
        imageView.setImageDrawable(rotatingPlane);
        rotatingPlane.start();
        /*float fractions2[] = new float[]{0f, 0.5f, 1f};
        ObjectAnimator objectAnimator = new ViewAnimatorBuilder(imageView).
                rotateX(fractions2, 0f, -180f, -180f).
                rotateY(fractions2, 0f, 0f, -180f).
                scale(fractions2,0f,1f,0f).
                alpha(fractions2,0f,1f,0f).
                duration(1200).
                easeInOut(fractions2)
                .build();
        objectAnimator.start();*/
    }
}
