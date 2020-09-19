package com.akari.ppx.common.utils;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.akari.ppx.common.constant.Const;
import com.akari.ppx.ui.channel.ChannelEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ChannelUtils {
    private static SharedPreferences mSharedPreferences;

    public static void initSP(SharedPreferences preferences) {
        ChannelUtils.mSharedPreferences = preferences;
    }

    @SuppressLint("ApplySharedPref")
    public static void setSPDataList(String tag, List<ChannelEntity> list) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(tag, new Gson().toJson(list));
        editor.apply();
    }

    public static List<ChannelEntity> getSPDataList(String tag) {
        return getDataList(mSharedPreferences.getString(tag, ""));
    }

    @SuppressLint("ApplySharedPref")
    public static void setDefaultChannel(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Const.KEY_DEFAULT_CHANNEL, name);
        editor.apply();
    }

    public static List<ChannelEntity> getDataList(String content) {
        try {
            List<ChannelEntity> dataList;
            Gson gson = new Gson();
            dataList = gson.fromJson(content, new TypeToken<List<ChannelEntity>>() {
            }.getType());
            return dataList;
        } catch (Exception ignored) {
            return null;
        }
    }
}
