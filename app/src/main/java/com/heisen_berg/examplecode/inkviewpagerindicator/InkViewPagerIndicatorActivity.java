package com.heisen_berg.examplecode.inkviewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.ui.ElasticDragDismissLayout;
import com.heisen_berg.examplecode.ui.InkPageIndicator;
import com.heisen_berg.examplecode.ui.ParallaxPageTransformer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InkViewPagerIndicatorActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.view_pager_indicator)
    InkPageIndicator inkPageIndicator;
    @BindView(R.id.draggable_frame)
    ElasticDragDismissLayout dragDismissLayout;

    ParallaxPageTransformer parallaxPageTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ink_view_pager_indicator);
        ButterKnife.bind(this);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        parallaxPageTransformer = new ParallaxPageTransformer()
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.text_view, 2f, 2f))
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.tv, 4f, 4f));
        viewPager.setPageTransformer(true, parallaxPageTransformer);
        inkPageIndicator.setViewPager(viewPager);

        dragDismissLayout.addListener(
                new ElasticDragDismissLayout.SystemChromeFader(this) {
                    @Override
                    public void onDragDismissed() {
                        if (dragDismissLayout.getTranslationY() < 0) {
                            getWindow().setReturnTransition(
                                    TransitionInflater.from(InkViewPagerIndicatorActivity.this)
                                            .inflateTransition(R.transition.layout_return_downward));
                        }
                        finishAfterTransition();
                    }
                });
    }

    private class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            String[] lines = {"This is again an example from the ocean of Nick buther's Material Design library. \n\nThat is known as the Ink ViewPager Indicator.",
                    "You can also check it's code on Nick butcher's Github library Plaid. But again as i said Plaid is ocean, that's why I sort out some awesome features from it and showing here.\n\nDemo of Ink ViewPager Indicator is shown at the bottom of this activity. That is working as a active and inactive indicator for this viewpager.",
                    "As I said is a grand master in the field of Material Android Application development. And Ink ViewPager Indicator is again a great example of his mastery."};

            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_ink_view_pager_indicator, container, false);
            TextView textView = (TextView) view.findViewById(R.id.text_view);
            textView.setText(lines[position]);
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
