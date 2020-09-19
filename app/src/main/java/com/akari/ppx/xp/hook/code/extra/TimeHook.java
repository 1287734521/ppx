package com.akari.ppx.xp.hook.code.extra;

import com.akari.ppx.common.utils.Utils;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.getLongField;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.newInstance;

public class TimeHook extends XC_MethodHook {
    /**
     * 显示注册&出黑屋时间
     */
    private final ClassLoader cl;

    public TimeHook(ClassLoader cl) {
        this.cl = cl;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        ArrayList mAchievements = (ArrayList) getObjectField(param.thisObject, "achievements");
        if (mAchievements == null)
            mAchievements = new ArrayList();
        long createTime = getLongField(param.thisObject, "createTime");
        if (createTime != 0) {
            mAchievements.add(createAchievement("注册:", Utils.timestamp2Date(createTime)));
            try {
                long expireTime = getLongField(getObjectField(((ArrayList) callMethod(param.thisObject, "getPunishmentList")).get(0), "status"), "expireTime");
                if (expireTime != -1)
                    mAchievements.add(createAchievement("出黑屋:", Utils.timestamp2Date(expireTime)));
            } catch (Exception ignored) {
            }
        }
        param.setResult(mAchievements);
    }

    private Object createAchievement(String description, String time) {
        Object achievementInfo = newInstance(findClass("com.sup.android.mi.usercenter.model.UserInfo$AchievementInfo", cl));
        callMethod(achievementInfo, "setDescription", description + time);
        callMethod(achievementInfo, "setIcon", "https://akari.net.cn/ppx/icon.png");
        callMethod(achievementInfo, "setSchema", "akari://" + time.replace(':', '='));
        return achievementInfo;
    }
}
