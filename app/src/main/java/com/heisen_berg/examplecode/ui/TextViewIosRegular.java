package com.heisen_berg.examplecode.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by heisen-berg on 21/02/18.
 */

public class TextViewIosRegular extends AppCompatTextView {

    public TextViewIosRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewIosRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewIosRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        try {
            if (!isInEditMode()) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ios_font_regular.otf");
                setTypeface(tf);
            }
        } catch (Exception ex){
            Log.i("xoxo", "init: "+ex.toString());
            return;
        }
    }
}