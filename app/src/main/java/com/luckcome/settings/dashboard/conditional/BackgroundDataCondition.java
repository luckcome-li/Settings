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
package com.luckcome.settings.dashboard.conditional;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.NetworkPolicyManager;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.luckcome.settings.R;
import com.luckcome.settings.Settings;

public class BackgroundDataCondition extends Condition {

    public BackgroundDataCondition(ConditionManager manager) {
        super(manager);
    }

    @Override
    public void refreshState() {
        setActive(NetworkPolicyManager.from(mManager.getContext()).getRestrictBackground());
    }

    @Override
    public Icon getIcon() {
        return Icon.createWithResource(mManager.getContext(), R.drawable.ic_data_saver);
    }

    @Override
    public CharSequence getTitle() {
        return mManager.getContext().getString(R.string.condition_bg_data_title);
    }

    @Override
    public CharSequence getSummary() {
        return mManager.getContext().getString(R.string.condition_bg_data_summary);
    }

    @Override
    public CharSequence[] getActions() {
        return new CharSequence[] { mManager.getContext().getString(R.string.condition_turn_off) };
    }

    @Override
    public void onPrimaryClick() {
        mManager.getContext().startActivity(new Intent(mManager.getContext(),
                Settings.DataUsageSummaryActivity.class));
    }

    @Override
    public int getMetricsConstant() {
        return MetricsEvent.SETTINGS_CONDITION_BACKGROUND_DATA;
    }

    @Override
    public void onActionClick(int index) {
        if (index == 0) {
            NetworkPolicyManager.from(mManager.getContext()).setRestrictBackground(false);
            setActive(false);
        } else {
            throw new IllegalArgumentException("Unexpected index " + index);
        }
    }
}
