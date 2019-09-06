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

package com.luckcome.settings.dashboard.suggestions;

import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;

import com.android.ims.ImsManager;
import com.luckcome.settings.Settings.WifiCallingSuggestionActivity;
import com.luckcome.settings.overlay.FeatureFactory;
import com.android.settingslib.drawer.Tile;

/**
 * The Home of all stupidly dynamic Settings Suggestions checks.
 */
public class SuggestionsChecks {

    private static final String TAG = "SuggestionsChecks";
    private final Context mContext;


    public SuggestionsChecks(Context context) {
        mContext = context.getApplicationContext();
    }

    public boolean isSuggestionComplete(Tile suggestion) {
        ComponentName component = suggestion.intent.getComponent();
        String className = component.getClassName();
		if (className.equals(WifiCallingSuggestionActivity.class.getName())) {
            return isWifiCallingUnavailableOrEnabled();
        }

        final SuggestionFeatureProvider provider =
                FeatureFactory.getFactory(mContext).getSuggestionFeatureProvider(mContext);

        return provider.isSuggestionCompleted(mContext, component);
    }

    private boolean isDeviceSecured() {
        KeyguardManager km = mContext.getSystemService(KeyguardManager.class);
        return km.isKeyguardSecure();
    }


    public boolean isWifiCallingUnavailableOrEnabled() {
        if (!ImsManager.isWfcEnabledByPlatform(mContext) ||
                !ImsManager.isWfcProvisionedOnDevice(mContext)) {
            return true;
        }
        return ImsManager.isWfcEnabledByUser(mContext)
                && ImsManager.isNonTtyOrTtyOnVolteEnabled(mContext);
    }




}
