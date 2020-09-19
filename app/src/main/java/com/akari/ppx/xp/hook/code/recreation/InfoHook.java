package com.akari.ppx.xp.hook.code.recreation;

import com.akari.ppx.common.utils.InfoUtils;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;

import static com.akari.ppx.common.constant.Const.AUTHOR_ID;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.getLongField;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.newInstance;
import static de.robv.android.xposed.XposedHelpers.setLongField;
import static de.robv.android.xposed.XposedHelpers.setObjectField;

public class InfoHook extends XC_MethodHook {
    private final ClassLoader cl;
    private final InfoUtils mInfo;
    private final boolean mStatus1;
    private final boolean mStatus2;
    private String mName = "";

    public InfoHook(ClassLoader cl, boolean status1, boolean status2) {
        this.cl = cl;
        mInfo = new InfoUtils();
        mStatus1 = status1;
        mStatus2 = status2;
    }

    public void setField(Object obj, String fieldName, String value) {
        if (!"".equals(value)) {
            try {
                setLongField(obj, fieldName, Long.parseLong(value));
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Object userInfo = param.args[0];
        if (getLongField(userInfo, "id") == AUTHOR_ID) {
            final String desc = "皮皮虾助手开发者"; // ^_^
            Object certifyInfo = newInstance(findClass("com.sup.android.mi.usercenter.model.UserInfo$CertifyInfo", cl));
            callMethod(certifyInfo, "setCertifyType", 2);
            callMethod(certifyInfo, "setDescription", desc);
            setObjectField(userInfo, "certifyInfo", certifyInfo);
            setObjectField(userInfo, "description", desc);
        }
        if (mStatus1) {
            if ("".equals(mName)) {
                try {
                    mName = (String) getObjectField(callMethod(callStaticMethod(findClass("com.sup.android.module.usercenter.UserCenterService", cl), "getInstance"), "getMyUserInfo"), "name");
                } catch (Exception ignored) {
                    mName = "";
                }
            }
            if (getObjectField(userInfo, "name").equals(mName)) {
                if (mStatus2) {
                    Object Punishment = newInstance(findClass("com.sup.android.mi.usercenter.model.UserInfo$Punishment", cl));
                    setObjectField(Punishment, "shortDesc", "已被移送小黑屋");
                    ArrayList list = new ArrayList();
                    list.add(Punishment);
                    setObjectField(userInfo, "punishmentList", list);
                }
                int certifyType = Integer.parseInt(mInfo.getCertifyType());
                if (certifyType > 0 && certifyType <= 4) {
                    Object certifyInfo = newInstance(findClass("com.sup.android.mi.usercenter.model.UserInfo$CertifyInfo", cl));
                    callMethod(certifyInfo, "setCertifyType", certifyType);
                    String desc = mInfo.getCertifyDesc();
                    callMethod(certifyInfo, "setDescription", !"".equals(desc) ? desc : "阿勒，忘填描述了？");
                    setObjectField(userInfo, "certifyInfo", certifyInfo);
                }
                String name = mInfo.getUserName();
                if (!"".equals(name))
                    setObjectField(userInfo, "name", name);
                String desc = mInfo.getDescription();
                if (!"".equals(desc))
                    setObjectField(userInfo, "description", desc);
                setField(userInfo, "likeCount", mInfo.getLikeCount());
                setField(userInfo, "followersCount", mInfo.getFollowersCount());
                setField(userInfo, "followingCount", mInfo.getFollowingCount());
                setField(userInfo, "point", mInfo.getPoint());
            }
        }
    }
}
