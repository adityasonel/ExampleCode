package com.heisen_berg.examplecode.elasticdraglayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;

import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.ui.ElasticDragDismissLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heisen-berg on 22/02/18.
 */

public class ElasticDragLayoutActivity extends Activity {

    @BindView(R.id.draggable_frame)
    ElasticDragDismissLayout draggableFrame;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elastic_drag_layout);
        ButterKnife.bind(this);

        draggableFrame.addListener(new ElasticDragDismissLayout.SystemChromeFader(this){
            @Override
            public void onDragDismissed() {
                if (draggableFrame.getTranslationY() < 0){
                    getWindow().setReturnTransition(TransitionInflater.from(ElasticDragLayoutActivity.this)
                            .inflateTransition(R.transition.layout_return_downward));
                }
                finishAfterTransition();
            }
        });
    }
}
