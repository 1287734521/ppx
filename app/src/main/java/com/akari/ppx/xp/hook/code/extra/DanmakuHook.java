package com.akari.ppx.xp.hook.code.extra;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.setBooleanField;

public class DanmakuHook extends XC_MethodHook {
    /**
     * 解锁高级弹幕权限
     */
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        setBooleanField(param.getResult(), "canSendAdvanceDanmaku", true);
    }
}
