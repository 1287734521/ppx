package com.akari.ppx.xp.hook.code.beta;

import android.webkit.CookieManager;

import de.robv.android.xposed.XC_MethodReplacement;

import static de.robv.android.xposed.XposedBridge.invokeOriginalMethod;

public class DeviceHook extends XC_MethodReplacement {
    /**
     * 放置标志&清除Cookie
     */
    private static boolean start;

    public static boolean hasStart() {
        return start;
    }

    @Override
    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
        start = true;
        CookieManager.getInstance().removeAllCookie();
        invokeOriginalMethod(param.method, param.thisObject, param.args);
        start = false;
        return null;
    }
}
