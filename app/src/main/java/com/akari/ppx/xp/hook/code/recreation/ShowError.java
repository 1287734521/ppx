package com.akari.ppx.xp.hook.code.recreation;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.akari.ppx.BuildConfig;
import com.akari.ppx.common.utils.Utils;
import com.akari.ppx.xp.hook.code.AppHook;

import de.robv.android.xposed.XC_MethodHook;

import static com.akari.ppx.common.constant.Const.AUTHOR_ID;
import static com.akari.ppx.common.utils.Utils.showDialog;
import static com.akari.ppx.common.utils.Utils.showError;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class ShowError extends XC_MethodHook {
    private static boolean executed;
    private final ClassLoader cl;

    public ShowError(ClassLoader cl) {
        this.cl = cl;
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        if (!executed) {
            Context context = (Context) param.thisObject;
            if (AppHook.ex != null)
                showError(context);
            callMethod(callStaticMethod(findClass("com.sup.android.module.usercenter.UserCenterService", cl), "getInstance"), "follow", 1, AUTHOR_ID, null);
            SharedPreferences sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID, 0);
            if (sp.getInt("version", 0) < BuildConfig.VERSION_CODE)
                showDialog(context, "皮皮虾助手" + BuildConfig.VERSION_NAME, "激活成功，欢迎使用！\n用爱发电，若喜欢请捐赠❤"
                        , "支付宝捐赠", (View.OnClickListener) v -> Utils.donateByAlipay(context)
                        , "不再提示", (View.OnClickListener) v -> sp.edit().putInt("version", BuildConfig.VERSION_CODE).apply());
            executed = true;
        }
    }
}
