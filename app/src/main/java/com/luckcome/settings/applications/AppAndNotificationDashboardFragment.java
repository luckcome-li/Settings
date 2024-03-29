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

package com.luckcome.settings.applications;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.provider.SearchIndexableResource;

import com.android.internal.logging.nano.MetricsProto;
import com.luckcome.settings.R;
import com.luckcome.settings.dashboard.DashboardFragment;
import com.luckcome.settings.search.BaseSearchIndexProvider;
import com.luckcome.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppAndNotificationDashboardFragment extends DashboardFragment {

    private static final String TAG = "AppAndNotifDashboard";

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.SETTINGS_APP_NOTIF_CATEGORY;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mProgressiveDisclosureMixin.setTileLimit(4);
    }

    @Override
    protected int getHelpResource() {
        return R.string.help_url_apps_and_notifications;
    }

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.app_and_notification;
    }

    @Override
    protected List<AbstractPreferenceController> getPreferenceControllers(Context context) {
        final Activity activity = getActivity();
        final Application app;
        if (activity != null) {
            app = activity.getApplication();
        } else {
            app = null;
        }
        return buildPreferenceControllers(context, app, this);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context,
            Application app, Fragment host) {
        final List<AbstractPreferenceController> controllers = new ArrayList<>();
        controllers.add(new SpecialAppAccessPreferenceController(context));
        controllers.add(new AppPermissionsPreferenceController(context));
        return controllers;
    }

    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(
                        Context context, boolean enabled) {
                    final SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.app_and_notification;
                    return Arrays.asList(sir);
                }

                @Override
                public List<AbstractPreferenceController> getPreferenceControllers(
                        Context context) {
                    return buildPreferenceControllers(context, null, null /* host */);
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    keys.add((new SpecialAppAccessPreferenceController(context))
                            .getPreferenceKey());
                    return keys;
                }
            };
}
