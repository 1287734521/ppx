package com.akari.ppx.xp.hook.code.core;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.getIntField;

public class AdHook extends XC_MethodHook {
    /**
     * 去除广告
     */
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (getIntField(param.thisObject, "showType") == 0)
            param.setResult(null);
    }
}
