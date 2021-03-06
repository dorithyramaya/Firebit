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
package com.owellox.firebit.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Observe the state of user authentication. This is a lifecycle-aware component.
 */
public class AuthObserver implements LifecycleObserver {
    private final FirebaseAuth.AuthStateListener authStateListener;
    private final AuthObserver.Callback callback;
    private final Lifecycle lifecycle;

    /**
     * Connect this observer.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void connect() {
        if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            FirebaseAuth.getInstance().addAuthStateListener(this.authStateListener);
        }
    }

    /**
     * Disconnect this observer.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public final void disconnect() {
        FirebaseAuth.getInstance().removeAuthStateListener(this.authStateListener);
    }

    /**
     * A lifecycle-aware component for authentication state.
     */
    public AuthObserver(@NonNull Lifecycle lifecycle, @NonNull AuthObserver.Callback callback) {
        this.callback = callback;
        this.lifecycle = lifecycle;
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            public final void onAuthStateChanged(@NonNull FirebaseAuth auth) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    AuthObserver.this.callback.onSignedIn(user);
                } else {
                    AuthObserver.this.callback.onSignedOut();
                }
            }
        };
    }

    /**
     * Pass this callback to the constructor of {@link AuthObserver} to be notified when user's authentication state
     * has changed.
     */
    public interface Callback {
        /**
         * Called whenever right after the observer has been connected and the user is currently signed in.
         * Additionally, this method will be called whenever the user has recently signed-in.
         *
         * @param user The user that's recently signed-in.
         */
        void onSignedIn(@NonNull FirebaseUser user);

        /**
         * Called whenever right after the observer has been connected and the user is not signed in.
         * Additionally, this method will be called whenever the user has recently signed-out.
         */
        void onSignedOut();
    }
}