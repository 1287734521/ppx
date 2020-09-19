package com.akari.ppx.xp.hook.code.auto;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;

public class BrowseHook2 extends XC_MethodHook {
    /**
     * 内容刷新后切换页面
     */
    private final BrowseHook mBrowseHook;
    private final int mBrowseFrequency;

    public BrowseHook2(BrowseHook browseHook, int browseFrequency) {
        mBrowseHook = browseHook;
        mBrowseFrequency = browseFrequency;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Object viewPager = mBrowseHook.getViewPager();
        if (viewPager != null) {
            if (BrowseHook.autoBrowse)
                callMethod(viewPager, "postDelayed", new ItemThread(viewPager), mBrowseFrequency);
            else BrowseHook.autoBrowse = true;
        }
    }
}
