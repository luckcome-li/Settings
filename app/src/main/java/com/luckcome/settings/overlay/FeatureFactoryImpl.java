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

package com.luckcome.settings.overlay;

import android.app.AppGlobals;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.UserManager;
import android.support.annotation.Keep;

import com.luckcome.settings.applications.IPackageManagerWrapperImpl;
import com.luckcome.settings.applications.PackageManagerWrapperImpl;


import com.luckcome.settings.core.instrumentation.MetricsFeatureProvider;
import com.luckcome.settings.dashboard.DashboardFeatureProvider;
import com.luckcome.settings.dashboard.DashboardFeatureProviderImpl;
import com.luckcome.settings.dashboard.suggestions.SuggestionFeatureProvider;
import com.luckcome.settings.dashboard.suggestions.SuggestionFeatureProviderImpl;

import com.luckcome.settings.search.SearchFeatureProvider;
import com.luckcome.settings.search.SearchFeatureProviderImpl;
import com.luckcome.settings.security.SecurityFeatureProvider;
import com.luckcome.settings.security.SecurityFeatureProviderImpl;
import com.luckcome.settings.users.UserFeatureProvider;
import com.luckcome.settings.users.UserFeatureProviderImpl;

/**
 * {@link FeatureFactory} implementation for AOSP Settings.
 */
@Keep
public class FeatureFactoryImpl extends FeatureFactory {

    private MetricsFeatureProvider mMetricsFeatureProvider;
    private DashboardFeatureProviderImpl mDashboardFeatureProvider;
    private SearchFeatureProvider mSearchFeatureProvider;
    private SecurityFeatureProvider mSecurityFeatureProvider;
    private SuggestionFeatureProvider mSuggestionFeatureProvider;
    private UserFeatureProvider mUserFeatureProvider;

    @Override
    public SupportFeatureProvider getSupportFeatureProvider(Context context) {
        return null;
    }

    @Override
    public MetricsFeatureProvider getMetricsFeatureProvider() {
        if (mMetricsFeatureProvider == null) {
            mMetricsFeatureProvider = new MetricsFeatureProvider();
        }
        return mMetricsFeatureProvider;
    }

    @Override
    public DashboardFeatureProvider getDashboardFeatureProvider(Context context) {
        if (mDashboardFeatureProvider == null) {
            mDashboardFeatureProvider = new DashboardFeatureProviderImpl(context);
        }
        return mDashboardFeatureProvider;
    }







    @Override
    public SearchFeatureProvider getSearchFeatureProvider() {
        if (mSearchFeatureProvider == null) {
            mSearchFeatureProvider = new SearchFeatureProviderImpl();
        }
        return mSearchFeatureProvider;
    }

    @Override
    public SurveyFeatureProvider getSurveyFeatureProvider(Context context) {
        return null;
    }

    @Override
    public SecurityFeatureProvider getSecurityFeatureProvider() {
        if (mSecurityFeatureProvider == null) {
            mSecurityFeatureProvider = new SecurityFeatureProviderImpl();
        }
        return mSecurityFeatureProvider;
    }

    @Override
    public SuggestionFeatureProvider getSuggestionFeatureProvider(Context context) {
        if (mSuggestionFeatureProvider == null) {
            mSuggestionFeatureProvider = new SuggestionFeatureProviderImpl(context);
        }
        return mSuggestionFeatureProvider;
    }

    @Override
    public UserFeatureProvider getUserFeatureProvider(Context context) {
        if (mUserFeatureProvider == null) {
            mUserFeatureProvider = new UserFeatureProviderImpl(context);
        }
        return mUserFeatureProvider;
    }


}
