/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.luckcome.settings.search;

import android.provider.SearchIndexableResource;
import android.support.annotation.DrawableRes;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.XmlRes;
import android.text.TextUtils;
import com.luckcome.settings.DisplaySettings;
import com.luckcome.settings.EncryptionAndCredential;
import com.luckcome.settings.LegalSettings;
import com.luckcome.settings.R;
import com.luckcome.settings.ScreenshotSetting;
import com.luckcome.settings.applications.AppAndNotificationDashboardFragment;
import com.luckcome.settings.applications.DefaultAppSettings;
import com.luckcome.settings.applications.SpecialAccessSettings;
import com.luckcome.settings.applications.assist.ManageAssist;

import com.luckcome.settings.display.AmbientDisplaySettings;
import com.luckcome.settings.display.ScreenZoomSettings;

import com.luckcome.settings.security.LockscreenDashboardFragment;
import com.luckcome.settings.support.SupportDashboardActivity;
import com.luckcome.settings.users.UserSettings;

import java.util.Collection;
import java.util.HashMap;

public final class SearchIndexableResources {

    /**
     * Identifies subsettings which have an {@link SearchIndexableResource#intentAction} but
     * whose intents should still be treated as subsettings inside of Settings.
     */
    public static final String SUBSETTING_TARGET_PACKAGE = "subsetting_target_package";

    @XmlRes
    public static final int NO_DATA_RES_ID = 0;

    @VisibleForTesting
    static final HashMap<String, SearchIndexableResource> sResMap = new HashMap<>();

    @VisibleForTesting
    static void addIndex(Class<?> indexClass, @XmlRes int xmlResId,
            @DrawableRes int iconResId) {
        addIndex(indexClass, xmlResId, iconResId, null /* targetAction */);
    }

    @VisibleForTesting
    static void addIndex(Class<?> indexClass, @XmlRes int xmlResId,
            @DrawableRes int iconResId, String targetAction) {
        String className = indexClass.getName();
        SearchIndexableResource resource =
                new SearchIndexableResource(0, xmlResId, className, iconResId);

        if (!TextUtils.isEmpty(targetAction)) {
            resource.intentAction = targetAction;
            resource.intentTargetPackage = SUBSETTING_TARGET_PACKAGE;
        }

        sResMap.put(className, resource);
    }

    static {

        addIndex(ScreenZoomSettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_display);
        addIndex(DisplaySettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_display,
                "android.settings.DISPLAY_SETTINGS");
        addIndex(AmbientDisplaySettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_display);
        addIndex(AppAndNotificationDashboardFragment.class, NO_DATA_RES_ID,
                R.drawable.ic_settings_applications);

        addIndex(DefaultAppSettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_applications);
        addIndex(ManageAssist.class, NO_DATA_RES_ID, R.drawable.ic_settings_applications);
        addIndex(SpecialAccessSettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_applications);
        addIndex(UserSettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_multiuser);
        addIndex(EncryptionAndCredential.class, NO_DATA_RES_ID, R.drawable.ic_settings_security);


        addIndex(LegalSettings.class, NO_DATA_RES_ID, R.drawable.ic_settings_about);

        addIndex(ScreenshotSetting.class,
                R.xml.screenshot,
                R.drawable.ic_settings_screenshot);
        addIndex(LockscreenDashboardFragment.class, R.xml.security_lockscreen_settings,
                R.drawable.ic_settings_security);
        addIndex(SupportDashboardActivity.class, NO_DATA_RES_ID, R.drawable.ic_help);
    }

    private SearchIndexableResources() {
    }

    public static int size() {
        return sResMap.size();
    }

    public static SearchIndexableResource getResourceByName(String className) {
        return sResMap.get(className);
    }

    public static Collection<SearchIndexableResource> values() {
        return sResMap.values();
    }
}
