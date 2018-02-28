package com.heisen_berg.examplecode.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by heisen-berg on 21/02/18.
 */

public class TextViewIosBold extends AppCompatTextView {

    public TextViewIosBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewIosBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewIosBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ios_font_bold.otf");
            setTypeface(tf);
        }
    }
}