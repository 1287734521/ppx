package com.akari.ppx.xp.hook.code.extra;

import de.robv.android.xposed.XC_MethodHook;

public class CopyHook2 extends XC_MethodHook {
    /**
     * 复制帖子文本
     */
    private final CopyHook mCopyHook;

    public CopyHook2(CopyHook copyHook) {
        mCopyHook = copyHook;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        String content = mCopyHook.getContent();
        if (content != null) {
            param.args[1] = content;
            mCopyHook.setContent(null);
        }
    }

}
