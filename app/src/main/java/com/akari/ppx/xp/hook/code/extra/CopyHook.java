package com.akari.ppx.xp.hook.code.extra;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;

public class CopyHook extends XC_MethodHook {
    /**
     * 获取帖子文本
     */
    private String mContent;

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        try {
            mContent = (String) callMethod(callMethod(param.args[0], "getFeedItem"), "getText");
        } catch (Exception ignored) {
        }
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
