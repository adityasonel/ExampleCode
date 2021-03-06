package com.heisen_berg.examplecode;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.heisen_berg.examplecode.blasttransition.BlastActivity;
import com.heisen_berg.examplecode.elasticdraglayout.ElasticDragLayoutActivity;
import com.heisen_berg.examplecode.fabtoggle.FabToggleActivity;
import com.heisen_berg.examplecode.inkviewpagerindicator.InkViewPagerIndicatorActivity;
import com.heisen_berg.examplecode.invisionproject.InvisionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        changeToolbarFont(toolbar, this);
    }

    public static void changeToolbarFont(Toolbar toolbar, Activity context) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(toolbar.getTitle())) {
                    Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/ios_font_bold.otf");
                    tv.setTypeface(tf);
                    tv.setLetterSpacing(0.06f);
                    break;
                }
            }
        }
    }

    public void startElasticDragLayoutActivity(View view) {
        startActivity(new Intent(MainActivity.this, ElasticDragLayoutActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void startInkViewPagerIndicatorActivity(View view) {
        startActivity(new Intent(MainActivity.this, InkViewPagerIndicatorActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void startFabToggleActivity(View view) {
        startActivity(new Intent(MainActivity.this, FabToggleActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void startBlastActivity(View view) {
        startActivity(new Intent(MainActivity.this, BlastActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void startInvisionProject(View view) {
        startActivity(new Intent(MainActivity.this, InvisionActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
