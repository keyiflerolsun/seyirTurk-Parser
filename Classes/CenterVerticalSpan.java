package com.swenauk.mainmenu.Classes;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes3.dex */
public class CenterVerticalSpan extends MetricAffectingSpan {
    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        textPaint.baselineShift += getBaselineShift(textPaint);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.baselineShift += getBaselineShift(textPaint);
    }

    private int getBaselineShift(TextPaint textPaint) {
        return (int) ((textPaint.ascent() + textPaint.descent()) / 2.0f);
    }
}
