package com.akari.ppx.common.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceDialogFragmentCompat;

import com.akari.ppx.common.constant.Const;

import org.jetbrains.annotations.NotNull;

public class ResetEditPreferenceDialogFragCompat extends PreferenceDialogFragmentCompat {

    private static final String SAVE_STATE_TEXT = "ResetEditPreferenceDialogFragCompat.text";
    private EditText mEditText;
    private CharSequence mText;
    private int mWhichClicked;

    public ResetEditPreferenceDialogFragCompat() {
        mWhichClicked = DialogInterface.BUTTON_NEUTRAL;
    }

    public static ResetEditPreferenceDialogFragCompat newInstance(String key) {
        ResetEditPreferenceDialogFragCompat fragment = new ResetEditPreferenceDialogFragCompat();
        Bundle b = new Bundle(1);
        b.putString("key", key);
        fragment.setArguments(b);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mText = savedInstanceState == null ? getResetEditPreference().getText() : savedInstanceState.getCharSequence(SAVE_STATE_TEXT);
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(SAVE_STATE_TEXT, mText);
    }

    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        mEditText = view.findViewById(android.R.id.edit);
        mEditText.requestFocus();
        if (mEditText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        } else {
            mEditText.setText(mText);
            mEditText.setSelection(mEditText.getText().length());
        }
    }

    protected boolean needInputMethod() {
        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
        mWhichClicked = which;
    }

    @Override
    public void onDismiss(@NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
        onDialogClosed();
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
    }

    private ResetEditPreference getResetEditPreference() {
        return (ResetEditPreference) getPreference();
    }

    protected void onDialogClosed() {
        if (mWhichClicked == DialogInterface.BUTTON_NEUTRAL) {
            return;
        }
        ResetEditPreference resetEditPreference = getResetEditPreference();
        String value;
        if (mWhichClicked == DialogInterface.BUTTON_POSITIVE) {
            value = mEditText.getText().toString();
        } else {
            value = resetEditPreference.getDefaultValue();
        }
        if (resetEditPreference.callChangeListener(value)) {
            resetEditPreference.setText(value);
            resetEditPreference.setSummary(("".equals(value) ? "" : Const.NOW) + value);
        }
    }
}
