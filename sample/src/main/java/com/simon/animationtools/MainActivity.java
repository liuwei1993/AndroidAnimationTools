package com.simon.animationtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import static com.simon.animationtools.Assets.colors;

public class MainActivity extends AppCompatActivity {

    public static AnimationDrawable[] drawables =
            {new WavingBars(), new WanderingCubes(), new FoldingCube(),
                    new CubeGrid(), new RotatingPlane(), new RotatingDots(),
                    new RotatingDots2(), new DoubleCircleBounce(), new MultiplePulse(),
                    new ThreeBounce(),new ChasingDots()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this,3));
        list.setAdapter(new Adapter());
    }

    private static class Adapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(drawables[position], position);
        }

        @Override
        public int getItemCount() {
            return drawables.length;
        }
    }

    static class Holder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        void bind(AnimationDrawable drawable, final int position) {
            itemView.setBackgroundColor(colors[position % colors.length]);
            imageView.setImageDrawable(drawable);
            drawable.start();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.start(v.getContext(), position);
                }
            });
        }

    }

}
