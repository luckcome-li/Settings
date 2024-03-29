/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.luckcome.settings;

import android.content.Context;
import android.provider.SearchIndexableResource;

import com.android.internal.hardware.AmbientDisplayConfiguration;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.luckcome.settings.R;
import com.luckcome.settings.dashboard.DashboardFragment;
import com.luckcome.settings.display.AmbientDisplayPreferenceController;
import com.luckcome.settings.display.AutoBrightnessPreferenceController;
import com.luckcome.settings.display.AutoRotatePreferenceController;
import com.luckcome.settings.display.BrightnessLevelPreferenceController;
import com.luckcome.settings.display.CameraGesturePreferenceController;
import com.luckcome.settings.display.ColorModePreferenceController;
import com.luckcome.settings.display.FontSizePreferenceController;
import com.luckcome.settings.display.HdmiSettingsPreferenceController;
import com.luckcome.settings.display.LiftToWakePreferenceController;
import com.luckcome.settings.display.NightDisplayPreferenceController;
import com.luckcome.settings.display.NightModePreferenceController;
import com.luckcome.settings.display.ScreenSaverPreferenceController;
import com.luckcome.settings.display.TapToWakePreferenceController;
import com.luckcome.settings.display.ThemePreferenceController;
import com.luckcome.settings.display.TimeoutPreferenceController;
import com.luckcome.settings.display.VrDisplayPreferenceController;
import com.luckcome.settings.display.WallpaperPreferenceController;
import com.luckcome.settings.search.BaseSearchIndexProvider;
import com.luckcome.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;

import java.util.ArrayList;
import java.util.List;

public class DisplaySettings extends DashboardFragment {
    private static final String TAG = "DisplaySettings";

    public static final String KEY_AUTO_BRIGHTNESS = "auto_brightness";
    public static final String KEY_DISPLAY_SIZE = "screen_zoom";

    private static final String KEY_SCREEN_TIMEOUT = "screen_timeout";
    private static final String KEY_AMBIENT_DISPLAY = "ambient_display";
    private static final String KET_HDMI_SETTINGS = "hdmi_settings";

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.DISPLAY;
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
    protected int getPreferenceScreenResId() {
        return R.xml.display_settings;
    }

    @Override
    protected List<AbstractPreferenceController> getPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getLifecycle());
    }

    @Override
    protected int getHelpResource() {
        return R.string.help_uri_display;
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(
            Context context, Lifecycle lifecycle) {
        final List<AbstractPreferenceController> controllers = new ArrayList<>();
        controllers.add(new AutoBrightnessPreferenceController(context, KEY_AUTO_BRIGHTNESS));
        controllers.add(new AutoRotatePreferenceController(context, lifecycle));
        controllers.add(new CameraGesturePreferenceController(context));
        controllers.add(new FontSizePreferenceController(context));
        controllers.add(new LiftToWakePreferenceController(context));
        controllers.add(new NightDisplayPreferenceController(context));
        controllers.add(new NightModePreferenceController(context));
        controllers.add(new ScreenSaverPreferenceController(context));
        controllers.add(new AmbientDisplayPreferenceController(
                context,
                new AmbientDisplayConfiguration(context),
                KEY_AMBIENT_DISPLAY));
        controllers.add(new TapToWakePreferenceController(context));
        controllers.add(new TimeoutPreferenceController(context, KEY_SCREEN_TIMEOUT));
        controllers.add(new VrDisplayPreferenceController(context));
        controllers.add(new WallpaperPreferenceController(context));
        controllers.add(new ThemePreferenceController(context));
        controllers.add(new BrightnessLevelPreferenceController(context, lifecycle));
        controllers.add(new ColorModePreferenceController(context));
        controllers.add(new HdmiSettingsPreferenceController(context, KET_HDMI_SETTINGS));
        return controllers;
    }

    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    final ArrayList<SearchIndexableResource> result = new ArrayList<>();

                    final SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.display_settings;
                    result.add(sir);
                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    keys.add(KEY_DISPLAY_SIZE);
                    keys.add(WallpaperPreferenceController.KEY_WALLPAPER);
                    keys.add(KEY_AMBIENT_DISPLAY);
                    return keys;
                }

                @Override
                public List<AbstractPreferenceController> getPreferenceControllers(Context context) {
                    return buildPreferenceControllers(context, null);
                }
            };
}
