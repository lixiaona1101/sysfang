package com.example.lixiaona.study.view;

import android.content.Context;
import android.util.AttributeSet;


public class AutoScrollTextView extends android.support.v7.widget.AppCompatTextView {
    public AutoScrollTextView(Context context) {
        super(context);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {//必须重写，且返回值是true
        return true;
    }
}

