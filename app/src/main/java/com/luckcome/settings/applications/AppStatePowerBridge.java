/*
 * Copyright (C) 2015 The Android Open Source Project
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
package com.luckcome.settings.applications;

import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.ApplicationsState.AppEntry;
import com.android.settingslib.applications.ApplicationsState.AppFilter;
import com.android.settingslib.applications.ApplicationsState.CompoundFilter;

import java.util.ArrayList;

/**
 * Connects data from the PowerWhitelistBackend to ApplicationsState.
 */
public class AppStatePowerBridge extends AppStateBaseBridge {


    public AppStatePowerBridge(ApplicationsState appState, Callback callback) {
        super(appState, callback);
    }

    @Override
    protected void loadAllExtraInfo() {
        ArrayList<AppEntry> apps = mAppSession.getAllApps();
        final int N = apps.size();
        for (int i = 0; i < N; i++) {
            AppEntry app = apps.get(i);
        }
    }

    @Override
    protected void updateExtraInfo(AppEntry app, String pkg, int uid) {
    }

    public static class HighPowerState {
        public boolean isHighPower;
        public boolean isSystemHighPower;
    }

    public static final AppFilter FILTER_POWER_WHITELISTED = new CompoundFilter(
            ApplicationsState.FILTER_WITHOUT_DISABLED_UNTIL_USED, new AppFilter() {
        @Override
        public void init() {
        }

        @Override
        public boolean filterApp(AppEntry info) {
            return info.extraInfo == Boolean.TRUE;
        }
    });
}
