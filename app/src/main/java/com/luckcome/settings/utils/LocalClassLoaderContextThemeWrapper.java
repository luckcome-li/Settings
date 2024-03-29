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

package com.luckcome.settings.utils;

import android.annotation.StyleRes;
import android.content.Context;
import android.view.ContextThemeWrapper;

/**
 * {@link ContextThemeWrapper} that provides a local classloader. This guarantees we have a
 * valid {@link ClassLoader} when base context is from an external app.
 */
public class LocalClassLoaderContextThemeWrapper extends ContextThemeWrapper {

    private Class mLocalClass;

    public LocalClassLoaderContextThemeWrapper(Class clazz, Context base,
            @StyleRes int themeResId) {
        super(base, themeResId);
        mLocalClass = clazz;
    }

    @Override
    public ClassLoader getClassLoader() {
        return mLocalClass.getClassLoader();
    }
}
