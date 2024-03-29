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
package com.luckcome.settings.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.luckcome.settings.R;
import com.luckcome.settings.Settings.LegacySupportActivity;
import com.luckcome.settings.overlay.FeatureFactory;
import com.luckcome.settings.overlay.SupportFeatureProvider;
import com.luckcome.settings.search.BaseSearchIndexProvider;
import com.luckcome.settings.search.Indexable;
import com.luckcome.settings.search.SearchIndexableRaw;

import java.util.ArrayList;
import java.util.List;

/**
 * Trampoline activity that decides which version of support should be shown to the user.
 */
public class SupportDashboardActivity extends Activity implements Indexable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportFeatureProvider supportFeatureProvider = FeatureFactory.getFactory(this)
                .getSupportFeatureProvider(this);

        // try to launch support v2 if we have the feature provider
        if (supportFeatureProvider != null && supportFeatureProvider.isSupportV2Enabled()) {
            supportFeatureProvider.startSupportV2(this);
        } else {
            startActivity(new Intent(this, LegacySupportActivity.class));
        }
        finish();
    }

    /**
     * For Search.
     */
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                private static final String SUPPORT_SEARCH_INDEX_KEY = "support_dashboard_activity";

                @Override
                public List<SearchIndexableRaw> getRawDataToIndex(Context context,
                                                                  boolean enabled) {

                    final List<SearchIndexableRaw> result = new ArrayList<>();

                    // Add the activity title
                    SearchIndexableRaw data = new SearchIndexableRaw(context);
                    data.title = context.getString(R.string.page_tab_title_support);
                    data.screenTitle = context.getString(R.string.settings_label);
                    data.summaryOn = context.getString(R.string.support_summary);
                    data.iconResId = R.drawable.ic_help;
                    data.intentTargetPackage = context.getPackageName();
                    data.intentTargetClass = SupportDashboardActivity.class.getName();
                    data.intentAction = Intent.ACTION_MAIN;
                    data.key = SUPPORT_SEARCH_INDEX_KEY;
                    result.add(data);

                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    final List<String> keys = super.getNonIndexableKeys(context);
                    if (!context.getResources().getBoolean(R.bool.config_support_enabled)) {
                        keys.add(SUPPORT_SEARCH_INDEX_KEY);
                    }
                    return keys;
                }
            };
}
