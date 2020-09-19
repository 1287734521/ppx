package com.akari.ppx.xp.hook.code.auto;

import de.robv.android.xposed.XC_MethodReplacement;

import static de.robv.android.xposed.XposedBridge.invokeOriginalMethod;

public class ShareHook extends XC_MethodReplacement {
    @Override
    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
        for (int i = 0; i < 99; i++)
            invokeOriginalMethod(param.method, param.thisObject, param.args);
        return invokeOriginalMethod(param.method, param.thisObject, param.args);
    }
}
