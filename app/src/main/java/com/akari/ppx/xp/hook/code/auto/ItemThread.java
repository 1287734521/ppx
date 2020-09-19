package com.akari.ppx.xp.hook.code.auto;

import static de.robv.android.xposed.XposedHelpers.callMethod;

public class ItemThread implements Runnable {
    private final Object mViewPager;

    public ItemThread(Object viewPager) {
        mViewPager = viewPager;
    }

    @Override
    public void run() {
        callMethod(mViewPager, "setCurrentItem", (int) callMethod(mViewPager, "getCurrentItem") + 1);
    }
}
