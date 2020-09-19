package com.akari.ppx.xp.hook.code;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.akari.ppx.BuildConfig;
import com.akari.ppx.common.constant.ClassConst;
import com.akari.ppx.common.constant.MethodConst;
import com.akari.ppx.common.utils.XSPUtils;
import com.akari.ppx.xp.hook.BaseHook;
import com.akari.ppx.xp.hook.code.auto.BrowseHook;
import com.akari.ppx.xp.hook.code.auto.BrowseHook2;
import com.akari.ppx.xp.hook.code.auto.CommentPauseHook;
import com.akari.ppx.xp.hook.code.auto.DiggAndCommentHook;
import com.akari.ppx.xp.hook.code.auto.DiggPauseHook;
import com.akari.ppx.xp.hook.code.auto.SendGodHook;
import com.akari.ppx.xp.hook.code.auto.ShareHook;
import com.akari.ppx.xp.hook.code.beta.DeviceHook;
import com.akari.ppx.xp.hook.code.beta.DeviceHook2;
import com.akari.ppx.xp.hook.code.beta.DeviceHook3;
import com.akari.ppx.xp.hook.code.beta.DeviceHook4;
import com.akari.ppx.xp.hook.code.core.AdHook;
import com.akari.ppx.xp.hook.code.core.ChannelHook;
import com.akari.ppx.xp.hook.code.core.ChannelHook2;
import com.akari.ppx.xp.hook.code.core.ChannelHook3;
import com.akari.ppx.xp.hook.code.core.ImageHook;
import com.akari.ppx.xp.hook.code.core.VideoHook;
import com.akari.ppx.xp.hook.code.extra.CopyHook;
import com.akari.ppx.xp.hook.code.extra.CopyHook2;
import com.akari.ppx.xp.hook.code.extra.DanmakuHook;
import com.akari.ppx.xp.hook.code.extra.MsgHook;
import com.akari.ppx.xp.hook.code.extra.TimeHook;
import com.akari.ppx.xp.hook.code.extra.TimeHook2;
import com.akari.ppx.xp.hook.code.extra.WordHook;
import com.akari.ppx.xp.hook.code.recreation.InfoHook;
import com.akari.ppx.xp.hook.code.recreation.ShowError;
import com.akari.ppx.xp.hook.code.view.BottomViewHook;
import com.akari.ppx.xp.hook.code.view.DetailButtonViewHook;
import com.akari.ppx.xp.hook.code.view.PublishButtonHook;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.akari.ppx.common.utils.ChannelUtils.getDataList;
import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.getStaticIntField;

public class AppHook implements BaseHook {
    public static ClassLoader cl;
    public static StringBuilder ex;
    private static int versionCode;

    private static void initHook(ClassLoader cl) {
        hookMethod(XSPUtils.saveImage(), ClassConst.saveImage, MethodConst.saveImage, new ImageHook(cl));

        hookMethod(XSPUtils.saveVideo(), ClassConst.saveVideo, MethodConst.saveVideo
                , Context.class, findClass(ClassConst.VideoModel, cl), findClass(ClassConst.VideoDownLoadConfig, cl)
                , findClass(ClassConst.IDownloadListener, cl), new VideoHook(cl));

        boolean removeRestrictions = XSPUtils.removeRestrictions();
        hookMethod(removeRestrictions, ClassConst.removeRestrictions1, MethodConst.removeRestrictions1, XC_MethodReplacement.returnConstant(true));
        hookMethod(removeRestrictions, ClassConst.removeRestrictions2, MethodConst.removeRestrictions2, XC_MethodReplacement.returnConstant(true));

        boolean removeAds = XSPUtils.removeAds();
        hookMethod(removeAds, ClassConst.removeAds1, MethodConst.removeAds1, new AdHook());
        hookMethod(removeAds, ClassConst.removeAds2, MethodConst.removeAds2, XC_MethodReplacement.returnConstant(null));
        hookMethod(removeAds, getProperClass(306, ClassConst.removeAds3), MethodConst.removeAds3, XC_MethodReplacement.returnConstant(false));
        hookMethod(removeAds, ClassConst.removeAds4, MethodConst.removeAds4, XC_MethodReplacement.DO_NOTHING);

        hookMethod(XSPUtils.removeTeenagerModeDialog(), ClassConst.removeTeenagerModeDialog, MethodConst.removeTeenagerModeDialog
                , Activity.class, XC_MethodReplacement.DO_NOTHING);

        hookMethod(XSPUtils.disableUpdate(), ClassConst.disableUpdate, MethodConst.disableUpdate, Context.class, XC_MethodReplacement.DO_NOTHING);

        boolean diyCategoryList = XSPUtils.diyCategoryList();
        hookMethod(diyCategoryList, ClassConst.diyCategoryList1, MethodConst.diyCategoryList1, List.class, new ChannelHook(getDataList(XSPUtils.getChannels()), cl));
        hookMethod(diyCategoryList, ClassConst.diyCategoryList2, MethodConst.diyCategoryList2, new ChannelHook2(XSPUtils.getDefaultChannel()));
        hookMethod(diyCategoryList, ClassConst.diyCategoryList3, MethodConst.diyCategoryList3, int.class, new ChannelHook3());
        hookMethod(diyCategoryList, ClassConst.diyCategoryList4, MethodConst.diyCategoryList4, XC_MethodReplacement.DO_NOTHING);

        boolean showRegisterEscapeTime = XSPUtils.showRegisterEscapeTime();
        hookMethod(showRegisterEscapeTime, ClassConst.showRegisterEscapeTime1, MethodConst.showRegisterEscapeTime1, new TimeHook(cl));
        hookMethod(showRegisterEscapeTime, ClassConst.showRegisterEscapeTime2, MethodConst.showRegisterEscapeTime2, Context.class, findClass(ClassConst.RouteIntent, cl), new TimeHook2(cl));

        boolean copyItem = XSPUtils.copyItem();
        CopyHook copyHook = new CopyHook();
        hookMethod(copyItem, ClassConst.copyItem1, MethodConst.copyItem1, findClass(ClassConst.AbsFeedCell, cl), copyHook);
        hookMethod(copyItem, ClassConst.copyItem2, MethodConst.copyItem2
                , Activity.class, String.class, findClass(ClassConst.SystemShareHelper$PLATFORM, cl)
                , Handler.class, new CopyHook2(copyHook));

        boolean unlockIllegalWord = XSPUtils.unlockIllegalWord();
        hookMethod(unlockIllegalWord, ClassConst.unlockIllegalWord1, MethodConst.unlockIllegalWord1, String.class, new WordHook());
        hookMethod(unlockIllegalWord, ClassConst.unlockIllegalWord2, MethodConst.unlockIllegalWord2, XC_MethodReplacement.returnConstant(null));

        hookMethod(XSPUtils.unlockDanmaku(), ClassConst.unlockDanmaku, MethodConst.unlockDanmaku, new DanmakuHook());

        hookMethod(XSPUtils.unlock1080P(), ClassConst.unlock1080P, MethodConst.unlock1080P, int.class, int.class, XC_MethodReplacement.returnConstant(true));

        hookMethod(XSPUtils.removeSendGodLimit(), ClassConst.removeSendGodLimit, MethodConst.removeSendGodLimit
                , new SendGodHook(XSPUtils.autoSendGodEnable(), XSPUtils.autoSendGodTimeLimit(), cl));

        hookMethod(XSPUtils.disableDoubleLayoutStyle(), ClassConst.disableDoubleLayoutStyle, MethodConst.disableDoubleLayoutStyle, XC_MethodReplacement.returnConstant(Boolean.FALSE));

        boolean bypassDeviceLimit = XSPUtils.bypassDeviceLimit();
        hookMethod(bypassDeviceLimit, ClassConst.bypassDeviceLimit1, MethodConst.bypassDeviceLimit1, new DeviceHook());
        hookMethod(bypassDeviceLimit, ClassConst.bypassDeviceLimit2, MethodConst.bypassDeviceLimit2, new DeviceHook2());
        hookMethod(bypassDeviceLimit, ClassConst.bypassDeviceLimit3, MethodConst.bypassDeviceLimit3, JSONObject.class, new DeviceHook3());
        hookMethod(bypassDeviceLimit, ClassConst.bypassDeviceLimit4, MethodConst.bypassDeviceLimit4, Map.class, boolean.class, new DeviceHook4());

        boolean autoBrowseEnable = XSPUtils.autoBrowseEnable();
        int autoBrowseFrequency = XSPUtils.autoBrowseFrequency();
        BrowseHook browseHook = new BrowseHook();
        hookMethod(autoBrowseEnable, getProperClass(310, ClassConst.autoBrowseEnable1), MethodConst.autoBrowseEnable1
                , findClass(ClassConst.LayoutInflater, cl), findClass(ClassConst.ViewGroup, cl), findClass(ClassConst.Bundle, cl), browseHook);

        hookMethod(autoBrowseEnable, ClassConst.autoBrowseEnable2, MethodConst.autoBrowseEnable2, Object.class, new BrowseHook2(browseHook, autoBrowseFrequency));

        boolean autoDiggEnable = XSPUtils.autoDiggEnable();
        boolean autoCommentEnable = XSPUtils.autoCommentEnable();
        hookMethod(autoBrowseEnable || autoDiggEnable || autoCommentEnable, getProperClass(310, ClassConst.AutoDiggAndCommentEnable), MethodConst.autoDiggAndCommentEnable
                , int.class, new DiggAndCommentHook(browseHook, autoBrowseEnable, autoBrowseFrequency, autoDiggEnable, XSPUtils.diggStyle(), autoCommentEnable, XSPUtils.autoCommentText(), cl));

        hookMethod(XSPUtils.autoDiggPause(), ClassConst.autoDiggPause, MethodConst.autoDiggPause
                , long.class, int.class, boolean.class, int.class, int.class, new DiggPauseHook());

        hookMethod(XSPUtils.autoCommentPause(), ClassConst.autoCommentPause, MethodConst.autoCommentPause, new CommentPauseHook());

        hookMethod(XSPUtils.modifyShareCounts(), ClassConst.modifyShareCounts, MethodConst.modifyShareCounts, long.class, int.class, new ShareHook());

        hookMethod(XSPUtils.removeBottomView(), ClassConst.removeBottomView, MethodConst.removeBottomView, boolean.class, new BottomViewHook());
        hookMethod(XSPUtils.removePublishButton(), ClassConst.removePublishButton, MethodConst.removePublishButton, boolean.class, new PublishButtonHook());
        boolean removeDetailBottomView = XSPUtils.removeDetailBottomView();
        hookConstructor(removeDetailBottomView, ClassConst.removeDetailBottomView
                , findClass(ClassConst.DockerContext, cl), findClass(ClassConst.DetailBottomView, cl), String.class, new DetailButtonViewHook());
        hookMethod(removeDetailBottomView, ClassConst.removeDetailBottomView, MethodConst.removeDetailBottomView, int.class, boolean.class, XC_MethodReplacement.DO_NOTHING);

        boolean zbEnable = XSPUtils.zbEnable();
        hookMethod(true, ClassConst.zbEnable, MethodConst.zbEnable
                , findClass(ClassConst.UserInfo, cl), int.class, new InfoHook(cl, zbEnable, XSPUtils.punishEnable()));

        hookMethod(XSPUtils.modifyMessageCounts(), ClassConst.modifyMessageCounts, MethodConst.modifyMessageCounts, String.class, Class.class, new MsgHook(zbEnable));

        hookMethod(true, ClassConst.showError, MethodConst.showError, boolean.class, new ShowError(cl));
    }

    private static void hookMethod(boolean isEnable, String className, String methodName, Object... parameterTypesAndCallback) {
        if (isEnable) {
            try {
                findAndHookMethod(className, cl, methodName, parameterTypesAndCallback);
            } catch (Error e) {
                ex = new StringBuilder(e.toString());
                StackTraceElement[] stack = e.getStackTrace();
                for (int i = 0; i < 3; i++)
                    ex.append('\n').append(stack[i]);
            }
        }
    }

    private static void hookConstructor(boolean isEnable, String className, Object... parameterTypesAndCallback) {
        if (isEnable) {
            try {
                findAndHookConstructor(className, cl, parameterTypesAndCallback);
            } catch (Error e) {
                ex = new StringBuilder(e.toString());
                StackTraceElement[] stack = e.getStackTrace();
                for (int i = 0; i < 3; i++)
                    ex.append('\n').append(stack[i]);
            }
        }
    }

    /**
     * 待完善
     */
    private static String getProperClass(int maxVersion, String[] classesName) {
        return versionCode < maxVersion ? classesName[0] : classesName[1];
    }

    @Override
    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!"com.sup.android.superb".equals(lpparam.packageName)) return;
        cl = lpparam.classLoader;
        versionCode = getStaticIntField(findClass(ClassConst.BuildConfig, cl), MethodConst.versionCode);
        XSPUtils.initXSP(new XSharedPreferences(BuildConfig.APPLICATION_ID));
        initHook(cl);
    }

}
