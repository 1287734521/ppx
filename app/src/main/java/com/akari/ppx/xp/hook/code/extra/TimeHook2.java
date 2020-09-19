package com.akari.ppx.xp.hook.code.extra;

import com.akari.ppx.common.utils.Utils;

import de.robv.android.xposed.XC_MethodReplacement;

import static de.robv.android.xposed.XposedBridge.invokeOriginalMethod;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class TimeHook2 extends XC_MethodReplacement {
    /**
     * 复制注册&出黑屋时间
     */
    private final ClassLoader cl;

    public TimeHook2(ClassLoader cl) {
        this.cl = cl;
    }

    @Override
    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
        if ("akari".equals((String) callMethod(param.args[1], "getScheme"))) {
            callMethod(callStaticMethod(
                    findClass("com.bytedance.news.common.service.manager.ServiceManager", cl), "getService"
                    , findClass("com.sup.android.i_sharecontroller.IBaseShareService", cl)), "copyLink"
                    , param.args[0], ((String) callMethod(param.args[1], "getHost")).replace('=', ':'));
            Utils.showToast("已复制到剪贴板");
        } else invokeOriginalMethod(param.method, param.thisObject, param.args);
        return null;
    }
}
