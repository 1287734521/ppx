package com.akari.ppx.xp.hook.code.extra;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.setObjectField;

public class MsgHook extends XC_MethodHook {
    private final boolean mZb;

    public MsgHook(boolean zb) {
        mZb = zb;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (mZb && "com.sup.android.m_message.data.t".equals(((Class) param.args[1]).getName())) {
            for (Object i : (List) getObjectField(getObjectField(param.getResult(), "data"), "count_map")) {
                setObjectField(i, "count", 100);
            }
        }
    }
}
