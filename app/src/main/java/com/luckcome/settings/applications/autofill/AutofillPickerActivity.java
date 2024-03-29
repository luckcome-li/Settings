/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.luckcome.settings.applications.autofill;

import android.content.Intent;
import android.os.Bundle;

import com.luckcome.settings.R;
import com.luckcome.settings.SettingsActivity;
import com.luckcome.settings.applications.defaultapps.DefaultAutofillPicker;

/**
 * Standalone activity used to launch a {@link DefaultAutofillPicker} fragment from a
 * {@link android.provider.Settings#ACTION_REQUEST_SET_AUTOFILL_SERVICE} intent.
 */
public class AutofillPickerActivity extends SettingsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        final String packageName = intent.getData().getSchemeSpecificPart();
        intent.putExtra(EXTRA_SHOW_FRAGMENT, DefaultAutofillPicker.class.getName());
        intent.putExtra(EXTRA_SHOW_FRAGMENT_TITLE_RESID, R.string.autofill_app);
        intent.putExtra(DefaultAutofillPicker.EXTRA_PACKAGE_NAME, packageName);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return super.isValidFragment(fragmentName)
                || DefaultAutofillPicker.class.getName().equals(fragmentName);
    }
}
