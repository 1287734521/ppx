package com.akari.ppx.xp.hook.code.core;

import de.robv.android.xposed.XC_MethodReplacement;

import static com.akari.ppx.common.constant.Const.CATEGORY_LIST_NAME;
import static com.akari.ppx.common.constant.Const.CATEGORY_LIST_TYPE;

public class ChannelHook2 extends XC_MethodReplacement {
    /**
     * 设置默认频道
     */
    private final String mDefaultChannel;

    public ChannelHook2(String defaultChannel) {
        mDefaultChannel = defaultChannel;
    }

    @Override
    protected Object replaceHookedMethod(MethodHookParam param) {
        int i = -1;
        for (String name : CATEGORY_LIST_NAME) {
            i++;
            if (name.contains(mDefaultChannel))
                break;
        }
        return CATEGORY_LIST_TYPE[i];
    }
}
