package com.akari.ppx.xp.hook.code.core;

import java.util.ArrayList;
import java.util.concurrent.FutureTask;

import de.robv.android.xposed.XC_MethodHook;

import static com.akari.ppx.common.utils.Utils.showToast;
import static de.robv.android.xposed.XposedHelpers.callMethod;

public class VideoHook extends XC_MethodHook {
    /**
     * 下载视频
     */
    private final ClassLoader cl;

    public VideoHook(ClassLoader cl) {
        this.cl = cl;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Object videoModel = param.args[1];
        FutureTask<String> task = new FutureTask<>(new WebTask(2, (String) callMethod(videoModel, "getUri"), cl));
        new Thread(task).start();
        callMethod(((ArrayList) callMethod(videoModel, "getUrlList")).get(0), "setUrl", task.get());
        showToast("视频保存中");
    }
}
