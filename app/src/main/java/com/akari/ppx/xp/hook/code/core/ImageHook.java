package com.akari.ppx.xp.hook.code.core;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import de.robv.android.xposed.XC_MethodReplacement;

import static com.akari.ppx.common.utils.Utils.showToast;
import static de.robv.android.xposed.XposedHelpers.callMethod;

public class ImageHook extends XC_MethodReplacement {
    /**
     * 下载图片
     */
    private final ClassLoader cl;

    public ImageHook(ClassLoader cl) {
        this.cl = cl;
    }

    private boolean isThumb(Object obj1, Object obj2) {
        return Math.abs((int) callMethod(obj1, "getWidth") - (int) callMethod(obj2, "getWidth")) < 5 && Math.abs((int) callMethod(obj1, "getHeight") - (int) callMethod(obj2, "getHeight")) < 5;
    }

    @Override
    protected Object replaceHookedMethod(MethodHookParam param) throws InterruptedException, ExecutionException {
        Object currentImage = ((ArrayList) callMethod(param.thisObject, "getImages")).get((int) callMethod(callMethod(param.thisObject, "getVpGallery"), "getCurrentItem"));
        ArrayList thumbs = (ArrayList) callMethod(param.thisObject, "getThumbs");
        boolean useCdn = false;
        String url = null;
        if (thumbs == null || !isThumb(currentImage, thumbs.get((int) callMethod(callMethod(param.thisObject, "getVpGallery"), "getCurrentItem")))) {
            url = "https://sf1-nhcdn-tos.pstatp.com/obj/" + callMethod(currentImage, "getUri");
            FutureTask<String> task = new FutureTask<>(new WebTask(1, url, cl));
            new Thread(task).start();
            if (!"image/webp".equals(task.get()))
                useCdn = true;
        }
        ArrayList urlList = (ArrayList) callMethod(currentImage, "getUrlList");
        boolean isGif = (boolean) callMethod(currentImage, "isGif");
        if (!useCdn) {
            url = (String) callMethod(urlList.get(0), "getUrl");
            url = url.substring(0, url.lastIndexOf('.')) + (isGif ? ".gif" : ".png");
        }
        callMethod(urlList.get(0), "setUrl", url);
        showToast("图片保存中");
        callMethod(param.thisObject, "downloadImage", urlList, isGif);
        return null;
    }
}
