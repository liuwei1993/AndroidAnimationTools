package com.simon.animationtools;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simon.animationtools.interpolator.KeyFrameInterpolator;
import com.simon.animationtools.utils.ViewAnimatorBuilder;
import com.simon.animationtools.view.InterpolatorCanvas;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float fractions[] = new float[]{0f,0.3f, 0.7f, 1f};
        KeyFrameInterpolator keyFrameInterpolator = KeyFrameInterpolator.pathInterpolator(0.21f, 0.53f, 0.56f, 0.8f, fractions);
//        Interpolator keyFrameInterpolator = Ease.inOut();
        InterpolatorCanvas canvas = (InterpolatorCanvas) findViewById(R.id.canvas);
        canvas.setInterpolator(keyFrameInterpolator);
        float fractions2[] = new float[]{0f, 0.5f, 1f};
        ObjectAnimator objectAnimator = new ViewAnimatorBuilder(canvas).
                rotateX(fractions2, 0f, -360f, -360f).
                rotateY(fractions2, 0f, 0f, -360f).
                duration(1200).
                easeInOut(fractions2)
                .build();
        objectAnimator.start();

    }
}
