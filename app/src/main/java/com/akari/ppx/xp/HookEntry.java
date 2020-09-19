package com.akari.ppx.xp;

import com.akari.ppx.xp.hook.BaseHook;
import com.akari.ppx.xp.hook.code.AppHook;
import com.akari.ppx.xp.hook.me.ModuleUtilsHook;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookLoadPackage {
    private final List<BaseHook> mHookList;

    {
        mHookList = new ArrayList<>();
        mHookList.add(new ModuleUtilsHook());
        mHookList.add(new AppHook());
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        for (BaseHook hook : mHookList)
            hook.onLoadPackage(lpparam);
    }
}
