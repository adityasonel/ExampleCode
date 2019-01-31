package com.heisen_berg.examplecode.invisionproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.ui.ParallaxPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvisionActivity extends AppCompatActivity {

    @BindView(R.id.fl_lottieanimview)
    FrameLayout flLottieAnimView;
    @BindView(R.id.vp_invisonproject)
    ViewPager vpInvisionProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invision);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            new Handler().postDelayed(() -> flLottieAnimView.setVisibility(View.GONE), 2200);
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(this,this);
        vpInvisionProject.setClipToPadding(false);
        vpInvisionProject.setPadding(46,0,46,0);

        ParallaxPageTransformer parallaxPageTransformer = new ParallaxPageTransformer()
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.iv_main, 2f, 2f));
        vpInvisionProject.setPageTransformer(false, parallaxPageTransformer);

        vpInvisionProject.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;
        private Context mContext;
        private Activity mActivity;

        Drawable[] image = {getBaseContext().getDrawable(R.drawable.relate),
                getBaseContext().getDrawable(R.drawable.invisioncraft),
                getBaseContext().getDrawable(R.drawable.nikerunning)};
        String[] mainLine = {"Relate UI Kit", "Invision Craft", "Nike Runnning"};
        String[] tagLine = {"7 PROJECTS", "6 PROJECTS", "14 PROJECTS"};

        public ViewPagerAdapter(Context mContext, Activity mActivity) {
            this.mContext = mContext;
            this.mActivity = mActivity;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_invision_project, container, false);

            ImageView ivMain = view.findViewById(R.id.iv_main);
            ivMain.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView tvMain = view.findViewById(R.id.tv_main);
            TextView tvTagline = view.findViewById(R.id.tv_tagline);

//            ivMain.setImageDrawable(image[position]);
            tvMain.setText(mainLine[position]);
            tvTagline.setText(tagLine[position]);

            view.setOnClickListener(v -> {
                Intent intent = new Intent(mActivity, ProjectViewActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(mActivity, ivMain, "iv_main");
                startActivity(intent, options.toBundle());
            });

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}