package com.akari.ppx.common.utils;

import com.akari.ppx.common.constant.Const;

import de.robv.android.xposed.XSharedPreferences;

public class XSPUtils {
    public static XSharedPreferences xsp;

    public static void initXSP(XSharedPreferences xsp) {
        XSPUtils.xsp = xsp;
    }

    private static boolean getBoolean(String key) {
        return xsp.getBoolean(key, false);
    }

    private static int getInt(String key, int defValue) {
        return Integer.parseInt(xsp.getString(key, String.valueOf(defValue)));
    }

    public static boolean saveImage() {
        return xsp.getBoolean(Const.KEY_SAVE_IMAGE, true);
    }

    public static boolean saveVideo() {
        return xsp.getBoolean(Const.KEY_SAVE_VIDEO, true);
    }

    public static boolean removeRestrictions() {
        return xsp.getBoolean(Const.KEY_REMOVE_RESTRICTIONS, true);
    }

    public static boolean removeAds() {
        return xsp.getBoolean(Const.KEY_REMOVE_ADS, true);
    }

    public static boolean disableUpdate() {
        return xsp.getBoolean(Const.KEY_DISABLE_UPDATE, true);
    }

    public static boolean removeTeenagerModeDialog() {
        return xsp.getBoolean(Const.KEY_REMOVE_TEENAGER_MODE_DIALOG, true);
    }

    public static boolean diyCategoryList() {
        return getBoolean(Const.KEY_DIY_CATEGORY_LIST);
    }

    public static String getChannels() {
        return xsp.getString(Const.KEY_MY_CHANNEL, "");
    }

    public static String getDefaultChannel() {
        return xsp.getString(Const.KEY_DEFAULT_CHANNEL, "推荐");
    }

    public static boolean showRegisterEscapeTime() {
        return getBoolean(Const.KEY_SHOW_REGISTER_ESCAPE_TIME);
    }

    public static boolean copyItem() {
        return getBoolean(Const.KEY_COPY_ITEM);
    }

    public static boolean unlockIllegalWord() {
        return getBoolean(Const.KEY_UNLOCK_ILLEGAL_WORD);
    }

    public static boolean unlockDanmaku() {
        return getBoolean(Const.KEY_UNLOCK_DANMAKU);
    }

    public static boolean unlock1080P() {
        return getBoolean(Const.KEY_UNLOCK_1080P_LIMIT);
    }

    public static boolean removeSendGodLimit() {
        return getBoolean(Const.KEY_REMOVE_SEND_GOD_LIMIT);
    }

    public static boolean disableDoubleLayoutStyle() {
        return getBoolean(Const.KEY_DISABLE_DOUBLE_LAYOUT_STYLE);
    }

    public static boolean bypassDeviceLimit() {
        return getBoolean(Const.KEY_BYPASS_DEVICE_LIMIT);
    }

    public static boolean autoBrowseEnable() {
        return getBoolean(Const.KEY_AUTO_BROWSE_ENABLE);
    }

    public static int autoBrowseFrequency() {
        return getInt(Const.KEY_AUTO_BROWSE_FREQUENCY, 2000);
    }

    public static boolean autoDiggEnable() {
        return getBoolean(Const.KEY_AUTO_DIGG_ENABLE);
    }

    public static int diggStyle() {
        return getInt(Const.KEY_DIGG_STYLE, 10);
    }

    public static boolean autoDiggPause() {
        return getBoolean(Const.KEY_AUTO_DIGG_PAUSE);
    }

    public static boolean autoCommentEnable() {
        return getBoolean(Const.KEY_AUTO_COMMENT_ENABLE);
    }

    public static String autoCommentText() {
        return xsp.getString(Const.KEY_AUTO_COMMENT_TEXT, "");
    }

    public static boolean autoCommentPause() {
        return getBoolean(Const.KEY_AUTO_COMMENT_PAUSE);
    }

    public static boolean autoSendGodEnable() {
        return getBoolean(Const.KEY_AUTO_SEND_GOD_ENABLE);
    }

    public static int autoSendGodTimeLimit() {
        return getInt(Const.KEY_AUTO_SEND_GOD_TIME_LIMIT, 21600);
    }

    public static boolean modifyShareCounts() {
        return getBoolean(Const.KEY_MODIFY_SHARE_COUNTS);
    }

    public static boolean removeBottomView() {
        return getBoolean(Const.KEY_REMOVE_BOTTOM_VIEW);
    }

    public static boolean removePublishButton() {
        return getBoolean(Const.KEY_REMOVE_PUBLISH_BUTTON);
    }

    public static boolean removeDetailBottomView() {
        return getBoolean(Const.KEY_REMOVE_DETAIL_BOTTOM_VIEW);
    }

    public static boolean zbEnable() {
        return getBoolean(Const.KEY_ZB_ENABLE);
    }

    public static boolean modifyMessageCounts() {
        return getBoolean(Const.KEY_MODIFY_MESSAGE_COUNTS);
    }

    public static boolean punishEnable() {
        return getBoolean(Const.KEY_PUNISH_ENABLE);
    }
}