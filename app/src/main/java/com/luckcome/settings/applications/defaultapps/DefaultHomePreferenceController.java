/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.luckcome.settings.applications.defaultapps;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.luckcome.settings.applications.PackageManagerWrapper;

import java.util.ArrayList;
import java.util.List;

public class DefaultHomePreferenceController extends DefaultAppPreferenceController {

    static final IntentFilter HOME_FILTER;

    private final String mPackageName;

    static {
        HOME_FILTER = new IntentFilter(Intent.ACTION_MAIN);
        HOME_FILTER.addCategory(Intent.CATEGORY_HOME);
        HOME_FILTER.addCategory(Intent.CATEGORY_DEFAULT);
    }

    public DefaultHomePreferenceController(Context context) {
        super(context);
        mPackageName = mContext.getPackageName();
    }

    @Override
    public String getPreferenceKey() {
        return "default_home";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    protected DefaultAppInfo getDefaultAppInfo() {
        final ArrayList<ResolveInfo> homeActivities = new ArrayList<>();
        final ComponentName currentDefaultHome = mPackageManager.getHomeActivities(homeActivities);
        if (currentDefaultHome != null) {
            return new DefaultAppInfo(mPackageManager, mUserId, currentDefaultHome);
        }
        final ActivityInfo onlyAppInfo = getOnlyAppInfo();
        if (onlyAppInfo != null) {
            return new DefaultAppInfo(mPackageManager, mUserId, onlyAppInfo.getComponentName());
        }
        return null;
    }

    private ActivityInfo getOnlyAppInfo() {
        final List<ResolveInfo> homeActivities = new ArrayList<>();
        final List<ActivityInfo> appLabels = new ArrayList<>();

        mPackageManager.getHomeActivities(homeActivities);
        for (ResolveInfo candidate : homeActivities) {
            final ActivityInfo info = candidate.activityInfo;
            if (info.packageName.equals(mPackageName)) {
                continue;
            }
            appLabels.add(info);
        }
        return appLabels.size() == 1
                ? appLabels.get(0)
                : null;
    }

    public static boolean hasHomePreference(String pkg, Context context) {
        ArrayList<ResolveInfo> homeActivities = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        pm.getHomeActivities(homeActivities);
        for (int i = 0; i < homeActivities.size(); i++) {
            if (homeActivities.get(i).activityInfo.packageName.equals(pkg)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHomeDefault(String pkg, PackageManagerWrapper pm) {
        final ArrayList<ResolveInfo> homeActivities = new ArrayList<>();
        ComponentName def = pm.getHomeActivities(homeActivities);

        return def == null || def.getPackageName().equals(pkg);
    }
}
