package com.simon.animationtools;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.simon.animationtools.drawable.container.WanderingCubes;
import com.simon.animationtools.utils.AnimUtils;

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
        imageView.setBackgroundColor(Color.WHITE);
        final WanderingCubes drawable = new WanderingCubes();
//        drawable.setBgColor(Color.GREEN);
        imageView.setImageDrawable(drawable);
        drawable.start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnimUtils.isRunning(drawable)) {
                    drawable.stop();
                } else {
                    drawable.start();
                }
            }
        });
    }
}
