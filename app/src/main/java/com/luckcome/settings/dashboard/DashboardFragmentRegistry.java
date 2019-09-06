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

package com.luckcome.settings.dashboard;

import android.util.ArrayMap;

import com.luckcome.settings.DisplaySettings;
import com.luckcome.settings.applications.AppAndNotificationDashboardFragment;
import com.luckcome.settings.applications.DefaultAppSettings;
import com.luckcome.settings.security.LockscreenDashboardFragment;
import com.android.settingslib.drawer.CategoryKey;

import java.util.Map;

/**
 * A registry to keep track of which page hosts which category.
 */
public class DashboardFragmentRegistry {

    /**
     * Map from parent fragment to category key. The parent fragment hosts child with
     * category_key.
     */
    public static final Map<String, String> PARENT_TO_CATEGORY_KEY_MAP;

    /**
     * Map from category_key to parent. This is a helper to look up which fragment hosts the
     * category_key.
     */
    public static final Map<String, String> CATEGORY_KEY_TO_PARENT_MAP;

    static {
        PARENT_TO_CATEGORY_KEY_MAP = new ArrayMap<>();

        PARENT_TO_CATEGORY_KEY_MAP.put(AppAndNotificationDashboardFragment.class.getName(),
                CategoryKey.CATEGORY_APPS);
        PARENT_TO_CATEGORY_KEY_MAP.put(DefaultAppSettings.class.getName(),
                CategoryKey.CATEGORY_APPS_DEFAULT);
        PARENT_TO_CATEGORY_KEY_MAP.put(DisplaySettings.class.getName(),
                CategoryKey.CATEGORY_DISPLAY);

        PARENT_TO_CATEGORY_KEY_MAP.put(LockscreenDashboardFragment.class.getName(),
                CategoryKey.CATEGORY_SECURITY_LOCKSCREEN);

        CATEGORY_KEY_TO_PARENT_MAP = new ArrayMap<>(PARENT_TO_CATEGORY_KEY_MAP.size());

        for (Map.Entry<String, String> parentToKey : PARENT_TO_CATEGORY_KEY_MAP.entrySet()) {
            CATEGORY_KEY_TO_PARENT_MAP.put(parentToKey.getValue(), parentToKey.getKey());
        }
    }
}
