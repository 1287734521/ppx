package com.akari.ppx.xp.hook.code.beta;

import org.json.JSONObject;

import java.util.Random;

import de.robv.android.xposed.XC_MethodHook;

public class DeviceHook3 extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        if (DeviceHook.hasStart()) {
            JSONObject object = (JSONObject) param.args[0];
            object.put("openudid", Integer.toString(new Random().nextInt(233333)));
            object.remove("install_id");
            object.remove("device_id");
            object.remove("oaid");
        }
    }
}
