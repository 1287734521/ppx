package com.akari.ppx.xp.hook.me;

import com.akari.ppx.BuildConfig;
import com.akari.ppx.common.utils.ModuleUtils;
import com.akari.ppx.xp.hook.BaseHook;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class ModuleUtilsHook implements BaseHook {
    @Override
    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (BuildConfig.APPLICATION_ID.equals(lpparam.packageName))
            findAndHookMethod(ModuleUtils.class.getName(), lpparam.classLoader, "isModuleEnabled_Xposed", XC_MethodReplacement.returnConstant(true));
    }
}
