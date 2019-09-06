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

package com.luckcome.settings.search;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.view.accessibility.AccessibilityManager;

import com.luckcome.settings.R;
import com.luckcome.settings.dashboard.SiteMapManager;
import com.luckcome.settings.utils.AsyncLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AccessibilityServiceResultLoader extends AsyncLoader<Set<? extends SearchResult>> {

    private static final int NAME_NO_MATCH = -1;

    private List<String> mBreadcrumb;
    private SiteMapManager mSiteMapManager;
    @VisibleForTesting
    final String mQuery;
    private final AccessibilityManager mAccessibilityManager;
    private final PackageManager mPackageManager;


    public AccessibilityServiceResultLoader(Context context, String query,
            SiteMapManager mapManager) {
        super(context);
        mSiteMapManager = mapManager;
        mPackageManager = context.getPackageManager();
        mAccessibilityManager =
                (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        mQuery = query;
    }

    @Override
    public Set<? extends SearchResult> loadInBackground() {
        final Set<SearchResult> results = new HashSet<>();
        final Context context = getContext();

        final String screenTitle = context.getString(R.string.accessibility_settings);
        
        return results;
    }

    private List<String> getBreadCrumb() {
        if (mBreadcrumb == null || mBreadcrumb.isEmpty()) {
            final Context context = getContext();
        }
        return mBreadcrumb;
    }

    @Override
    protected void onDiscardResult(Set<? extends SearchResult> result) {

    }
}
