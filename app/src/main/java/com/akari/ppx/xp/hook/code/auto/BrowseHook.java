package com.akari.ppx.xp.hook.code.auto;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.getObjectField;

public class BrowseHook extends XC_MethodHook {
    /**
     * 获取viewPager
     */
    public static boolean autoBrowse = true;
    private Object mViewPager;

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        mViewPager = getObjectField(param.thisObject, "A");
    }

    public Object getViewPager() {
        return mViewPager;
    }
}
