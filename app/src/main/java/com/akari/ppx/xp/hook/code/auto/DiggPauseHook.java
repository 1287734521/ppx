package com.akari.ppx.xp.hook.code.auto;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;

public class DiggPauseHook extends XC_MethodHook {
    /**
     * 点赞频繁后暂停自动切换
     */
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Object result = param.getResult();
        if ((int) callMethod(result, "getStatusCode") != 0)
            BrowseHook.autoBrowse = false;
    }
}
