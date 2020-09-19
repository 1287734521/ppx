package com.akari.ppx.xp.hook.code.beta;

import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;

public class DeviceHook4 extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (DeviceHook.hasStart())
            ((Map) param.args[0]).remove("iid");
    }
}
