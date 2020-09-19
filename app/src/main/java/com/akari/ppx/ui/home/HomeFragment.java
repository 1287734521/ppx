package com.akari.ppx.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.akari.ppx.R;
import com.akari.ppx.common.constant.Const;
import com.akari.ppx.common.preference.ResetEditPreference;
import com.akari.ppx.common.preference.ResetEditPreferenceDialogFragCompat;
import com.akari.ppx.common.utils.ModuleUtils;
import com.akari.ppx.common.utils.Utils;
import com.akari.ppx.ui.channel.ChannelActivity;

import java.util.Objects;

public class HomeFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private final HomeActivity mContext;

    public HomeFragment(HomeActivity context) {
        mContext = context;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (ModuleUtils.isModuleEnabled_Xposed() || ModuleUtils.isModuleEnabled_Taichi(mContext)) return;
        ActionBar actionBar = ((HomeActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle(String.format("%s [%s]", actionBar.getTitle(), "未激活"));
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.setPreferenceWorldWritable(mContext);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        ((Preference) Objects.requireNonNull(findPreference(Const.KEY_DIY_CATEGORY_LIST))).setOnPreferenceChangeListener(this);
        ((Preference) Objects.requireNonNull(findPreference(Const.KEY_REMOVE_BOTTOM_VIEW))).setOnPreferenceChangeListener(this);
        ((Preference) Objects.requireNonNull(findPreference(Const.KEY_DONATE))).setOnPreferenceClickListener(this);
        ((Preference) Objects.requireNonNull(findPreference(Const.KEY_JOIN_QQ_GROUP))).setOnPreferenceClickListener(this);
        ((Preference) Objects.requireNonNull(findPreference(Const.KEY_HIDE_LAUNCHER_ICON))).setOnPreferenceChangeListener(this);
        ((Preference) Objects.requireNonNull(findPreference(Const.KEY_SOURCE_CODE))).setOnPreferenceClickListener(this);
        setSummary(Const.KEY_AUTO_BROWSE_FREQUENCY);
        setSummary(Const.KEY_AUTO_COMMENT_TEXT);
        setSummary(Const.KEY_USER_NAME);
        setSummary(Const.KEY_CERTIFY_DESC);
        setSummary(Const.KEY_DESCRIPTION);
        setSummary(Const.KEY_LIKE_COUNT);
        setSummary(Const.KEY_FOLLOWERS_COUNT);
        setSummary(Const.KEY_FOLLOWING_COUNT);
        setSummary(Const.KEY_POINT);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case Const.KEY_DONATE:
                Utils.donateByAlipay(mContext);
                break;
            case Const.KEY_JOIN_QQ_GROUP:
                Utils.joinQQGroup(mContext);
                break;
            case Const.KEY_SOURCE_CODE:
                Utils.showGitPage(mContext);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean checked = (Boolean) newValue;
        switch (preference.getKey()) {
            case Const.KEY_DIY_CATEGORY_LIST:
                if (checked)
                    mContext.startActivity(new Intent(mContext, ChannelActivity.class));
                return true;
            case Const.KEY_REMOVE_BOTTOM_VIEW: {
                if (checked)
                    ((SwitchPreference) Objects.requireNonNull(findPreference(Const.KEY_REMOVE_PUBLISH_BUTTON))).setChecked(false);
                return true;
            }
            case Const.KEY_HIDE_LAUNCHER_ICON: {
                Utils.hideIcon(mContext, checked);
                return true;
            }

        }

        return false;
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        boolean handled = false;
        if (preference instanceof ResetEditPreference) {
            DialogFragment dialogFragment = ResetEditPreferenceDialogFragCompat.newInstance(preference.getKey());
            FragmentManager fm = mContext.getSupportFragmentManager();
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(fm, "android.support.v7.preference.PreferenceFragment.DIALOG");
            handled = true;
        }
        if (!handled)
            super.onDisplayPreferenceDialog(preference);
    }

    private void setSummary(String key) {
        ResetEditPreference pref = findPreference(key);
        try {
            String text = Objects.requireNonNull(pref).getText();
            if (!"".equals(text)) {
                pref.setSummary(Const.NOW.concat(text));
                return;
            }
        } catch (NullPointerException ignored) {
        }
        Objects.requireNonNull(pref).setSummary("");
    }

}
