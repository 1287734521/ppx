package com.akari.ppx.xp.hook.code.core;

import com.akari.ppx.ui.channel.ChannelEntity;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.newInstance;

public class ChannelHook extends XC_MethodHook {
    /**
     * 管理频道
     */
    private final List<ChannelEntity> mTargetList;
    private final ClassLoader cl;

    public ChannelHook(List<ChannelEntity> targetList, ClassLoader cl) {
        mTargetList = targetList;
        this.cl = cl;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        List beforeList = (List) param.args[0], afterList = new ArrayList();
        //beforeList.add(createWebChannel("Test", "https://akari.net.cn/ppx/", "https://akari.net.cn/ppx/channel.jpg", 174, 116));
        List<String> nameList = new ArrayList<>();
        for (Object item : beforeList)
            nameList.add((String) callMethod(item, "getListName"));
        if (mTargetList == null)
            afterList = beforeList;
        else {
            for (ChannelEntity t : mTargetList) {
                int i = -1;
                for (String s : nameList) {
                    i++;
                    if (t.getName().contains(s))
                        break;
                }
                afterList.add(beforeList.get(i));
            }
        }
        param.args[0] = afterList;
    }

    private Object createWebChannel(String channelName, String webUrl, String imageUrl, int width, int height) {
        Object categoryItem = newInstance(findClass("com.sup.superb.feedui.bean.CategoryItem", cl));
        callMethod(categoryItem, "setListName", channelName);
        callMethod(categoryItem, "setPrimaryListId", 233);
        callMethod(categoryItem, "setEventName", channelName);
        callMethod(categoryItem, "setViewType", 1);
        Object webViewData = newInstance(findClass("com.sup.superb.feedui.bean.WebViewData", cl));
        callMethod(webViewData, "setUrl", webUrl);
        callMethod(webViewData, "setImmersive", Boolean.TRUE);
        callMethod(categoryItem, "setWebViewData", webViewData);
        Object categoryItemStyle = newInstance(findClass("com.sup.superb.feedui.bean.CategoryItemStyle", cl));
        callMethod(categoryItemStyle, "setCategoryName", channelName);
        callMethod(categoryItemStyle, "setSelectedStyle", 3);
        callMethod(categoryItemStyle, "setUnselectedStyle", 3);
        Object imageModel = newInstance(findClass("com.sup.android.base.model.ImageModel", cl));
        callMethod(imageModel, "setWidth", width);
        callMethod(imageModel, "setHeight", height);
        ArrayList urlList = new ArrayList();
        urlList.add(newInstance(findClass("com.sup.android.base.model.ImageUrlModel", cl), imageUrl));
        callMethod(imageModel, "setUrlList", urlList);
        callMethod(categoryItemStyle, "setSelectedImageInfo", imageModel);
        callMethod(categoryItem, "setCategoryStyle", categoryItemStyle);
        return categoryItem;
    }

}
