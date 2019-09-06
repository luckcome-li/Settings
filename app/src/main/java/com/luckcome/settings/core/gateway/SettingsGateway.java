/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.luckcome.settings.core.gateway;

import com.luckcome.settings.CryptKeeperSettings;
import com.luckcome.settings.DisplaySettings;
import com.luckcome.settings.display.HdmiSettings;
import com.luckcome.settings.IccLockSettings;
import com.luckcome.settings.MasterClear;
import com.luckcome.settings.PrivacySettings;
import com.luckcome.settings.ScreenshotSetting;
import com.luckcome.settings.Settings;
import com.luckcome.settings.TestingSettings;

import com.luckcome.settings.applications.DefaultAppSettings;
import com.luckcome.settings.applications.AppAndNotificationDashboardFragment;
import com.luckcome.settings.applications.DrawOverlayDetails;
import com.luckcome.settings.applications.ExternalSourcesDetails;
import com.luckcome.settings.applications.ManageDomainUrls;
import com.luckcome.settings.applications.ProcessStatsSummary;
import com.luckcome.settings.applications.ProcessStatsUi;
import com.luckcome.settings.applications.SpecialAccessSettings;
import com.luckcome.settings.applications.UsageAccessDetails;
import com.luckcome.settings.applications.WriteSettingsDetails;
import com.luckcome.settings.applications.assist.ManageAssist;

import com.luckcome.settings.dashboard.SupportFragment;
import com.luckcome.settings.display.NightDisplaySettings;
import com.luckcome.settings.security.LockscreenDashboardFragment;
import com.luckcome.settings.support.SupportDashboardActivity;
import com.luckcome.settings.users.UserSettings;


public class SettingsGateway {

    /**
     * A list of fragment that can be hosted by SettingsActivity. SettingsActivity will throw a
     * security exception if the fragment it needs to display is not in this list.
     */
    public static final String[] ENTRY_FRAGMENTS = {
            ScreenshotSetting.class.getName(),
            HdmiSettings.class.getName(),
            DisplaySettings.class.getName(),
            ManageAssist.class.getName(),
            ProcessStatsUi.class.getName(),
            UsageAccessDetails.class.getName(),
            PrivacySettings.class.getName(),
            CryptKeeperSettings.class.getName(),
            UserSettings.class.getName(),
            SpecialAccessSettings.class.getName(),
            ProcessStatsUi.class.getName(),
            ProcessStatsSummary.class.getName(),
            DrawOverlayDetails.class.getName(),
            WriteSettingsDetails.class.getName(),
            ExternalSourcesDetails.class.getName(),
            DefaultAppSettings.class.getName(),
            IccLockSettings.class.getName(),
            TestingSettings.class.getName(),
            MasterClear.class.getName(),
            NightDisplaySettings.class.getName(),
            ManageDomainUrls.class.getName(),
            SupportFragment.class.getName(),
            AppAndNotificationDashboardFragment.class.getName(),
            LockscreenDashboardFragment.class.getName(),
    };

    public static final String[] SETTINGS_FOR_RESTRICTED = {
            // Home page
            Settings.NetworkDashboardActivity.class.getName(),
            Settings.ConnectedDeviceDashboardActivity.class.getName(),
            Settings.ScreenshotSettingsActivity.class.getName(),
            Settings.AppAndNotificationDashboardActivity.class.getName(),
            Settings.DisplaySettingsActivity.class.getName(),
            Settings.SoundSettingsActivity.class.getName(),
            Settings.StorageDashboardActivity.class.getName(),
            Settings.PowerUsageSummaryActivity.class.getName(),
            Settings.UserAndAccountDashboardActivity.class.getName(),
            Settings.AccessibilitySettingsActivity.class.getName(),
            Settings.SystemDashboardActivity.class.getName(),
            SupportDashboardActivity.class.getName(),
            // Home page > Network & Internet
            // Home page > Connected devices
            // Home page > Apps & Notifications
            Settings.UserSettingsActivity.class.getName(),
            Settings.ConfigureNotificationSettingsActivity.class.getName(),
            // Home page > Security & screen lock
            // Home page > System
            Settings.LanguageAndInputSettingsActivity.class.getName(),
            // Home page > Display
            Settings.HdmiSettingsActivity.class.getName(),
    };
}
