package com.akari.ppx.common.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.preference.EditTextPreference;

public class ResetEditPreference extends EditTextPreference {
    private String mDefaultValue;

    public ResetEditPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        Object result = super.onGetDefaultValue(a, index);
        mDefaultValue = (String) result;
        return result;
    }

    public String getDefaultValue() {
        return mDefaultValue;
    }

    @Override
    public void setDefaultValue(Object value) {
        super.setDefaultValue(value);
        mDefaultValue = (String) value;
    }
}
