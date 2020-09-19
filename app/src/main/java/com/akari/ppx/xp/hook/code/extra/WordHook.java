package com.akari.ppx.xp.hook.code.extra;

import de.robv.android.xposed.XC_MethodReplacement;

public class WordHook extends XC_MethodReplacement {
    /**
     * 不替换屏蔽词
     */
    @Override
    protected Object replaceHookedMethod(MethodHookParam param) {
        return param.args[0];
    }
}
