package com.simon.animationtools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simon.core.animationtools.drawable.AnimationDrawable;
import com.simon.core.animationtools.drawable.container.ChasingDots;
import com.simon.core.animationtools.drawable.container.CubeGrid;
import com.simon.core.animationtools.drawable.container.DoubleCircleBounce;
import com.simon.core.animationtools.drawable.container.FoldingCube;
import com.simon.core.animationtools.drawable.container.MultiplePulse;
import com.simon.core.animationtools.drawable.container.RotatingDots;
import com.simon.core.animationtools.drawable.container.RotatingDots2;
import com.simon.core.animationtools.drawable.container.ThreeBounce;
import com.simon.core.animationtools.drawable.container.WanderingCubes;
import com.simon.core.animationtools.drawable.container.WavingBars;
import com.simon.core.animationtools.drawable.shape.RotatingPlane;
import com.simon.core.animationtools.utils.ArgbEvaluator;

import static com.simon.animationtools.Assets.colors;

/**
 * test activity
 * Created by simon on 17-6-6.
 */

public class DetailActivity extends Activity {

    private static AnimationDrawable[] drawables =
            {new WavingBars(), new WanderingCubes(), new FoldingCube(),
                    new CubeGrid(), new RotatingPlane(), new RotatingDots(),
                    new RotatingDots2(), new DoubleCircleBounce(), new MultiplePulse(),
                    new ThreeBounce(),new ChasingDots()};

    public static void start(Context context, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return drawables.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_pager, null);

                ImageView imageView = (ImageView) view.findViewById(R.id.image);
                AnimationDrawable drawable = drawables[position];
                imageView.setImageDrawable(drawable);
                drawable.start();
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int color = (int) ArgbEvaluator.getInstance().evaluate(positionOffset,
                        colors[position % colors.length],
                        colors[(position + 1) % colors.length]);
                getWindow().getDecorView().setBackgroundColor(color);
            }

            @Override
            public void onPageSelected(int position) {
                getWindow().getDecorView().setBackgroundColor(colors[position % colors.length]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
    }
}
