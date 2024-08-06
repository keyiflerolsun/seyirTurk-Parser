package com.swenauk.mainmenu.Classes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

/* loaded from: classes3.dex */
public class EpisodeNumberPicker extends NumberPicker {
    public int override;

    public EpisodeNumberPicker(Context context) {
        super(context);
        this.override = 0;
    }

    public EpisodeNumberPicker(Context context, int r2) {
        super(context);
        this.override = 0;
        this.override = r2;
    }

    public EpisodeNumberPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.override = 0;
    }

    public EpisodeNumberPicker(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        this.override = 0;
    }

    public EpisodeNumberPicker(Context context, AttributeSet attributeSet, int r3, int r4) {
        super(context, attributeSet, r3, r4);
        this.override = 0;
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        super.addView(view);
        updateView(view);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int r2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, r2, layoutParams);
        updateView(view);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, layoutParams);
        updateView(view);
    }

    @Override // android.widget.NumberPicker
    public void setDisplayedValues(String[] strArr) {
        for (int r0 = 0; r0 < strArr.length; r0++) {
            if (strArr[r0].equals("" + this.override)) {
                strArr[r0] = strArr[r0] + " ▶";
            }
        }
        super.setDisplayedValues(strArr);
    }

    private void updateView(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            System.out.println("Tester: " + ((Object) textView.getText()));
            if (textView.getText().toString().equals("" + this.override)) {
                textView.setLines(3);
                textView.setTextSize(16.0f);
                textView.setTextColor(-16776961);
                textView.setPadding(20, 10, 20, 10);
                textView.setMaxWidth(10);
                textView.setSingleLine(false);
                textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView.requestLayout();
                textView.invalidate();
            }
        }
    }
}
