/*
 * Copyright (C) 2014 The Android Open Source Project
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

import android.content.Context;
import android.provider.SearchIndexableResource;

import com.android.settingslib.core.AbstractPreferenceController;

import java.util.List;

/**
 * Interface for classes whose instances can provide data for indexing.
 *
 * Classes implementing the Indexable interface must have a static field called
 * <code>SEARCH_INDEX_DATA_PROVIDER</code>, which is an object implementing the
 * {@link Indexable.SearchIndexProvider} interface.
 *
 * See {@link android.provider.SearchIndexableResource} and {@link SearchIndexableRaw}.
 *
 */
public interface Indexable {

    interface SearchIndexProvider {
        /**
         * Return a list of references for indexing.
         *
         * See {@link android.provider.SearchIndexableResource}
         *
         *
         * @param context the context.
         * @param enabled hint telling if the data needs to be considered into the search results
         *                or not.
         * @return a list of {@link android.provider.SearchIndexableResource} references.
         *         Can be null.
         */
        List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean enabled);

        /**
         * Return a list of raw data for indexing. See {@link SearchIndexableRaw}
         *
         * @param context the context.
         * @param enabled hint telling if the data needs to be considered into the search results
         *                or not.
         * @return a list of {@link SearchIndexableRaw} references. Can be null.
         */
        List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean enabled);

        /**
         * Return a list of data keys that cannot be indexed. See {@link SearchIndexableRaw}
         *
         * @param context the context.
         * @return a list of {@link SearchIndexableRaw} references. Can be null.
         */
        List<String> getNonIndexableKeys(Context context);

        /**
         * @param context
         * @return a list of {@link AbstractPreferenceController} for ResultPayload data during
         * Indexing.
         */
        List<AbstractPreferenceController> getPreferenceControllers(Context context);
    }
}
