package com.akari.ppx.xp.hook.code.auto;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.getBooleanField;
import static de.robv.android.xposed.XposedHelpers.getIntField;
import static de.robv.android.xposed.XposedHelpers.getLongField;
import static de.robv.android.xposed.XposedHelpers.newInstance;
import static de.robv.android.xposed.XposedHelpers.setLongField;

public class SendGodHook extends XC_MethodHook {
    /**
     * 解除送神限制&自动送神
     */
    private final boolean mAutoDigg;
    private final int mTimeLimit;
    private final ClassLoader cl;

    public SendGodHook(boolean autoDigg, int timeLimit, ClassLoader cl) {
        mAutoDigg = autoDigg;
        mTimeLimit = timeLimit;
        this.cl = cl;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (getLongField(param.thisObject, "aliasItemId") == 1 && !getBooleanField(param.thisObject, "hasLiked")) {
            setLongField(param.thisObject, "aliasItemId", 2);
            callMethod(newInstance(findClass("com.sup.android.detail.util.o", cl)), "a",
                    8, getLongField(param.thisObject, "commentId"), true, 10, 1);
            if (mAutoDigg && System.currentTimeMillis() / 1000 - getLongField(param.thisObject, "createTime") <= mTimeLimit)
                callMethod(newInstance(findClass("com.sup.android.m_comment.util.b.d", cl)), "a",
                        getLongField(param.thisObject, "itemId"), getLongField(param.thisObject, "commentId"), null);
            param.setResult(1);
            return;
        }
        if (getIntField(param.thisObject, "sendGodStatus") == 3) {
            if (getLongField(param.thisObject, "aliasItemId") == 2) {
                param.setResult(mAutoDigg && System.currentTimeMillis() / 1000 - getLongField(param.thisObject, "createTime") <= mTimeLimit ? 2 : 1);
                return;
            }
            setLongField(param.thisObject, "aliasItemId", 1);
            param.setResult(1);
        }
    }
}
