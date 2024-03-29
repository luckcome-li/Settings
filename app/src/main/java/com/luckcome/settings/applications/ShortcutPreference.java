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

package com.luckcome.settings.applications;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.Preference;

import com.android.internal.logging.nano.MetricsProto;
import com.luckcome.settings.SettingsActivity;
import com.luckcome.settings.Utils;

public class ShortcutPreference extends Preference {

    private final Class mTarget;
    private final String mPrefKey;
    private final int mTitle;

    public ShortcutPreference(Context context, Class target, String prefKey, int prefTitle,
            int title) {
        super(context);
        mTarget = target;
        mPrefKey = prefKey;
        mTitle = title;
        setTitle(prefTitle);
        setKey(mPrefKey);
    }

    @Override
    public void performClick() {
        super.performClick();
        Bundle bundle = new Bundle();
        bundle.putString(SettingsActivity.EXTRA_FRAGMENT_ARG_KEY, mPrefKey);
        Utils.startWithFragment(getContext(), mTarget.getName(), bundle, null, 0,
                mTitle, null, MetricsProto.MetricsEvent.VIEW_UNKNOWN);
    }
}
