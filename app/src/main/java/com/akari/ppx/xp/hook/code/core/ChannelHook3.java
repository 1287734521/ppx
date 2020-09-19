package com.akari.ppx.xp.hook.code.core;

import de.robv.android.xposed.XC_MethodHook;

public class ChannelHook3 extends XC_MethodHook {
    /**
     * 解决【关注】被挪到第一个频道的问题
     */
    private static int i = 0;

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        i++;
        if ((i & 1) != 0) {
            param.setResult(-1);
        }
    }
}
