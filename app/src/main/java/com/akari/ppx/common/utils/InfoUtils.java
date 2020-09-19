package com.akari.ppx.common.utils;

import com.akari.ppx.common.constant.Const;

import de.robv.android.xposed.XSharedPreferences;

public class InfoUtils {
    private final String certifyType;
    private final String certifyDesc;
    private final String userName;
    private final String description;
    private final String likeCount;
    private final String followersCount;
    private final String followingCount;
    private final String point;

    public InfoUtils() {
        XSharedPreferences xsp = XSPUtils.xsp;
        certifyType = xsp.getString(Const.KEY_CERTIFY_TYPE, "0");
        certifyDesc = xsp.getString(Const.KEY_CERTIFY_DESC, "");
        userName = xsp.getString(Const.KEY_USER_NAME, "");
        description = xsp.getString(Const.KEY_DESCRIPTION, "");
        likeCount = xsp.getString(Const.KEY_LIKE_COUNT, "");
        followersCount = xsp.getString(Const.KEY_FOLLOWERS_COUNT, "");
        followingCount = xsp.getString(Const.KEY_FOLLOWING_COUNT, "");
        point = xsp.getString(Const.KEY_POINT, "");
    }

    public String getUserName() {
        return userName;
    }

    public String getCertifyType() {
        return certifyType;
    }

    public String getCertifyDesc() {
        return certifyDesc;
    }

    public String getDescription() {
        return description;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public String getPoint() {
        return point;
    }

}
