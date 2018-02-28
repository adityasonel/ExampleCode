package com.heisen_berg.examplecode.fabtoggle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.ui.FabTransform;
import com.heisen_berg.examplecode.ui.MorphTransform;

import butterknife.BindView;

public class FabDetailActivity extends AppCompatActivity {

    @BindView(R.id.container)
    CardView container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_detail);

        if (!FabTransform.setup(this, container)) {
            MorphTransform.setup(this, container,
                    ContextCompat.getColor(this, R.color.background_light),
                    2);
        }

    }
}
