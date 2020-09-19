package com.akari.ppx.xp.hook.code.auto;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;

public class CommentPauseHook extends XC_MethodHook {
    /**
     * 评论频繁后暂停自动切换
     */
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (!(boolean) callMethod(param.getResult(), "b"))
            BrowseHook.autoBrowse = false;
    }
}
