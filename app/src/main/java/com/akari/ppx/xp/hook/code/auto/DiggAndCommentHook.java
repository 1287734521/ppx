package com.akari.ppx.xp.hook.code.auto;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.getStaticObjectField;
import static de.robv.android.xposed.XposedHelpers.newInstance;

public class DiggAndCommentHook extends XC_MethodHook {
    /**
     * 自动浏览&自动点赞&自动评论
     */
    private final BrowseHook mBrowseHook;
    private final boolean mAutoBrowse;
    private final int mBrowseFrequency;
    private final boolean mAutoDigg;
    private final int mDiggStyle;
    private final boolean mAutoComment;
    private final String mCommentText;
    private final long uid;
    private final ClassLoader cl;

    public DiggAndCommentHook(BrowseHook browseHook, boolean autoBrowse, int browseFrequency, boolean autoDigg, int diggStyle, boolean autoComment, String commentText, ClassLoader cl) {
        mBrowseHook = browseHook;
        mAutoBrowse = autoBrowse;
        mBrowseFrequency = browseFrequency;
        mAutoDigg = autoDigg;
        mDiggStyle = diggStyle;
        mAutoComment = autoComment;
        mCommentText = commentText;
        Object userCenter = callStaticMethod(findClass("com.bytedance.news.common.service.manager.ServiceManager", cl), "getService"
                , findClass("com.sup.android.mi.usercenter.IUserCenterService", cl));
        if (userCenter == null) {
            uid = 0L;
        } else {
            Object myUserInfo = callMethod(userCenter, "getMyUserInfo");
            uid = myUserInfo == null ? 0L : (long) callMethod(myUserInfo, "getId");
        }
        this.cl = cl;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (mAutoBrowse) {
            if (BrowseHook.autoBrowse) {
                Object viewPager = mBrowseHook.getViewPager();
                callMethod(viewPager, "postDelayed", new ItemThread(viewPager), (int) param.args[0] == 1 ? 1000 : mBrowseFrequency);
            } else BrowseHook.autoBrowse = true;
        }
        try {
            Object feedCell = ((ArrayList) getObjectField(param.thisObject, "q")).get((int) param.args[0]);
            if (mAutoDigg) {
                callMethod(newInstance(findClass("com.sup.android.detail.util.o", cl)), "a",
                        callMethod(feedCell, "getCellType"), callMethod(feedCell, "getCellId"), true, mDiggStyle, 2);
            }
            if (mAutoComment && !"".equals(mCommentText)) {
                callMethod(getStaticObjectField(findClass("com.sup.android.module.publish.publish.PublishLooper$enqueue$1", cl), "INSTANCE"), "invoke"
                        , newInstance(findClass("com.sup.android.mi.publish.bean.CommentBean", cl)
                                , mCommentText, uid, callMethod(feedCell, "getCellId"), callMethod(feedCell, "getCellType"), 0L, 0L, null
                                , "cell_detail", "input", false, 0, false, false, -1L));
            }
        } catch (Exception ignored) {
        }
    }
}
