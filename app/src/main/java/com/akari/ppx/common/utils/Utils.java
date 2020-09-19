package com.akari.ppx.common.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.akari.ppx.common.constant.Const;
import com.akari.ppx.xp.hook.code.AppHook;

import java.io.File;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.newInstance;

public class Utils {
    public static void showDialog(Context context, String title, String message, String positiveText, View.OnClickListener onPositiveClickListener, String negativeText, View.OnClickListener onNegativeClickListener) {
        Object builder = callMethod(callMethod(callMethod(callMethod(newInstance(findClass("com.sup.android.uikit.base.UIBaseDialogBuilder", AppHook.cl), context)
                , "setTitle", title)
                , "setMessage", message)
                , "setOnPositiveClickListener", onPositiveClickListener)
                , "setOnNegativeClickListener", onNegativeClickListener);
        if (positiveText != null)
            builder = callMethod(builder, "setPositiveText", positiveText);
        if (negativeText != null)
            builder = callMethod(builder, "setNegativeText", negativeText);
        callMethod(callMethod(builder, "create"), "show");
    }

    public static void showToast(String text) {
        Toast.makeText((Context) callMethod(callStaticMethod(findClass("android.app.ActivityThread", AppHook.cl), "currentApplication"), "getApplicationContext"), text, Toast.LENGTH_SHORT).show();
    }

    public static void showError(Context context) {
        showDialog(context, "皮皮虾助手出错", "QAQ出错啦！调用栈如下：\n" + AppHook.ex.toString() + "\n请选择合适的皮皮虾&皮皮虾助手版本"
                , "加群反馈", (View.OnClickListener) v -> joinQQGroup(context), null, null);
    }

    @SuppressLint({"SetWorldWritable", "SetWorldReadable"})
    public static void setPreferenceWorldWritable(Context context) {
        File file = getSharedPreferencesFile(context);
        if (!file.exists())
            return;
        for (int i = 0; i < 3; i++) {
            file.setExecutable(true, false);
            file.setWritable(true, false);
            file.setReadable(true, false);
            file = file.getParentFile();
            if (file == null)
                break;
        }
    }

    private static File getSharedPreferencesFile(Context context) {
        File dataDir = ContextCompat.getDataDir(context);
        File prefsDir = new File(dataDir, "shared_prefs");
        return new File(prefsDir, Const.XSPreferencesName + ".xml");
    }

    @SuppressLint("SimpleDateFormat")
    public static String timestamp2Date(long ts) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ts * 1000), new StringBuffer(), new FieldPosition(0)).toString();
    }

    public static void donateByAlipay(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(Const.ALIPAY_URI));
            context.startActivity(intent);
        } catch (Exception ignored) {
        }
    }

    public static void joinQQGroup(Context context) {
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse(Const.QQ_GROUP_URI));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception ignored) {
        }
    }

    public static void showGitPage(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(Const.GITHUB_URI));
            context.startActivity(intent);
        } catch (Exception ignored) {
        }
    }

    public static void hideIcon(Context context, boolean isHide) {
        PackageManager pm = context.getPackageManager();
        ComponentName launcherCN = new ComponentName(context, Const.HOME_ACTIVITY_ALIAS);
        int state = isHide ? PackageManager.COMPONENT_ENABLED_STATE_DISABLED : PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        if (pm.getComponentEnabledSetting(launcherCN) != state) {
            pm.setComponentEnabledSetting(launcherCN, state, PackageManager.DONT_KILL_APP);
        }
    }

}