package com.heisen_berg.examplecode.fabtoggle;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.elasticdraglayout.ElasticDragLayoutActivity;
import com.heisen_berg.examplecode.ui.ElasticDragDismissLayout;
import com.heisen_berg.examplecode.ui.FabTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FabToggleActivity extends AppCompatActivity {

    @BindView(R.id.draggable_frame)
    ElasticDragDismissLayout dragDismissLayout;
    @BindView(R.id.fab)
    ImageButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_toggle);
        ButterKnife.bind(this);

        dragDismissLayout.addListener(new ElasticDragDismissLayout.SystemChromeFader(this){
            @Override
            public void onDragDismissed() {
                if (dragDismissLayout.getTranslationY() < 0){
                    getWindow().setReturnTransition(TransitionInflater.from(FabToggleActivity.this)
                            .inflateTransition(R.transition.layout_return_downward));
                }
                finishAfterTransition();
            }
        });
    }

    public void fabClicked(View view) {
        Intent intent = new Intent(this, FabDetailActivity.class);
        FabTransform.addExtras(intent,
                ContextCompat.getColor(this, R.color.accent), R.drawable.ic_github);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, fab,
                getString(R.string.transition_designer_news_login));
        startActivity(intent, options.toBundle());
    }
}
