package com.akari.ppx.common.constant;

import com.akari.ppx.BuildConfig;

public interface Const {
    String NOW = "当前：";
    String ALIPAY_URI = "alipayqr://platformapi/startapp?saId=10000007&qrcode=https://qr.alipay.com/fkx16213vf1yql4hjgu1k5c";
    String QQ_GROUP_URI = "mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D6sN70t6K0eKimPrPECMNbigIH3BEI9E6";
    String GITHUB_URI = "https://github.com/Secack/ppx";
    String HOME_ACTIVITY_ALIAS = BuildConfig.APPLICATION_ID + ".HomeActivityAlias";
    String XSPreferencesName = BuildConfig.APPLICATION_ID + "_preferences";
    String[] CATEGORY_LIST_NAME = {"关注", "推荐", "视频", "颜值", "真香", "直播", "汽车", "放映厅|免费电影", "图片", "文字", "游戏", "小说"};
    int[] CATEGORY_LIST_TYPE = {2, 1, 4, 48, 27, 50, 28, 15, 10, 11, 16, 666666};
    long AUTHOR_ID = 50086989143L;

    String KEY_SAVE_IMAGE = "pref_save_image",
            KEY_SAVE_VIDEO = "pref_save_video",
            KEY_REMOVE_RESTRICTIONS = "pref_remove_restrictions",
            KEY_REMOVE_ADS = "pref_remove_ads",
            KEY_REMOVE_TEENAGER_MODE_DIALOG = "pref_remove_teenager_mode_dialog",
            KEY_DISABLE_UPDATE = "pref_disable_update",
            KEY_DIY_CATEGORY_LIST = "pref_diy_category_list",
            KEY_MY_CHANNEL = "pref_my_channel",
            KEY_OTHER_CHANNEL = "pref_other_channel",
            KEY_DEFAULT_CHANNEL = "pref_default_channel",
            KEY_SHOW_REGISTER_ESCAPE_TIME = "pref_show_register_escape_time",
            KEY_COPY_ITEM = "pref_copy_item",
            KEY_UNLOCK_ILLEGAL_WORD = "pref_unlock_illegal_word",
            KEY_UNLOCK_DANMAKU = "pref_unlock_danmaku",
            KEY_UNLOCK_1080P_LIMIT = "pref_unlock_1080p_limit",
            KEY_REMOVE_SEND_GOD_LIMIT = "pref_remove_send_god_limit",
            KEY_DISABLE_DOUBLE_LAYOUT_STYLE = "pref_disable_double_layout_style",
            KEY_BYPASS_DEVICE_LIMIT = "pref_bypass_device_limit",
            KEY_AUTO_BROWSE_ENABLE = "pref_auto_browse_enable",
            KEY_AUTO_BROWSE_FREQUENCY = "pref_auto_browse_frequency",
            KEY_AUTO_DIGG_ENABLE = "pref_auto_digg_enable",
            KEY_DIGG_STYLE = "pref_digg_style",
            KEY_AUTO_DIGG_PAUSE = "pref_auto_digg_pause",
            KEY_AUTO_COMMENT_ENABLE = "pref_auto_comment_enable",
            KEY_AUTO_COMMENT_TEXT = "pref_auto_comment_text",
            KEY_AUTO_COMMENT_PAUSE = "pref_auto_comment_pause",
            KEY_AUTO_SEND_GOD_ENABLE = "pref_auto_send_god_enable",
            KEY_AUTO_SEND_GOD_TIME_LIMIT = "pref_auto_send_god_time_limit",
            KEY_MODIFY_SHARE_COUNTS = "pref_modify_share_counts",
            KEY_MODIFY_MESSAGE_COUNTS = "pref_modify_message_counts",
            KEY_REMOVE_BOTTOM_VIEW = "pref_remove_bottom_view",
            KEY_REMOVE_PUBLISH_BUTTON = "pref_remove_publish_button",
            KEY_REMOVE_DETAIL_BOTTOM_VIEW = "pref_remove_detail_bottom_view",
            KEY_ZB_ENABLE = "pref_zb_enable",
            KEY_USER_NAME = "pref_user_name",
            KEY_PUNISH_ENABLE = "pref_punish_enable",
            KEY_CERTIFY_TYPE = "pref_certify_type",
            KEY_CERTIFY_DESC = "pref_certify_desc",
            KEY_DESCRIPTION = "pref_description",
            KEY_LIKE_COUNT = "pref_like_count",
            KEY_FOLLOWERS_COUNT = "pref_followers_count",
            KEY_FOLLOWING_COUNT = "pref_following_count",
            KEY_POINT = "pref_point",
            KEY_DONATE = "pref_donate_by_alipay",
            KEY_JOIN_QQ_GROUP = "pref_join_qq_group",
            KEY_HIDE_LAUNCHER_ICON = "pref_hide_launcher_icon",
            KEY_SOURCE_CODE = "pref_source_code";
}
