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

package com.luckcome.settings.applications;

/**
 * This interface replicates a subset of the android.content.pm.ActivityInfo. The interface
 * exists so that we can use a thin wrapper around the ActivityInfo in production code and a mock in
 * tests.
 */
public interface ActivityInfoWrapper {

    /**
     * Returns whether this activity supports picture-in-picture.
     */
    boolean supportsPictureInPicture();
}
