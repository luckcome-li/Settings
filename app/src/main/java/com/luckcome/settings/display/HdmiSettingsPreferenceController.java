/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.luckcome.settings.display;

import android.content.Context;
import android.os.SystemProperties;
import android.support.v7.preference.Preference;

import com.luckcome.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

public class HdmiSettingsPreferenceController extends AbstractPreferenceController implements
        PreferenceControllerMixin{

    private final String mHdmiSettingsKey;

    public HdmiSettingsPreferenceController(Context context, String key) {
        super(context);
        mHdmiSettingsKey = key;
    }

    @Override
    public boolean isAvailable() {
        return "true".equals(SystemProperties.get("ro.rk.hdmisetting"));
    }

    @Override
    public String getPreferenceKey() {
        return mHdmiSettingsKey;
    }

}
