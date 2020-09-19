package com.akari.ppx.xp.hook.code.beta;

import de.robv.android.xposed.XC_MethodHook;

public class DeviceHook2 extends XC_MethodHook {
    /**
     * 重置设备注册查询时间
     */
    private static boolean executed;

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (!executed) {
            param.setResult(-1L);
            executed = true;
        }
    }
}
