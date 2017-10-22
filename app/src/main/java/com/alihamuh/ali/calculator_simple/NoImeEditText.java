package com.alihamuh.ali.calculator_simple;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Ali on 5/6/2017.
 */

public class NoImeEditText extends EditText {
    public NoImeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }


}
