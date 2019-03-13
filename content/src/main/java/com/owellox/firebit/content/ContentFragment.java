/*
 * Copyright (C) 2018 Owellox Firebit
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
package com.owellox.firebit.content;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Base class for fragments that support empty state.
 */
public abstract class ContentFragment extends Fragment {
    private @Nullable OnEmptyStateListener onEmptyStateListener;

    /**
     * Notify this class that the content is empty.
     */
    public final void notifyEmptyState() {
        if (onEmptyStateListener != null) {
            onEmptyStateListener.onEmptyState();
        }
    }

    /**
     * Set the listener to invoke when {@link #notifyEmptyState()} is called.
     */
    public final void setOnEmptyStateListener(@Nullable OnEmptyStateListener listener) {
        onEmptyStateListener = listener;
    }
}