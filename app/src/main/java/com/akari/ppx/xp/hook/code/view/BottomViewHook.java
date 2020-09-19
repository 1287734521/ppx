package com.akari.ppx.xp.hook.code.view;

import de.robv.android.xposed.XC_MethodHook;

public class BottomViewHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        param.args[0] = true;
    }
}
