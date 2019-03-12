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

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Base class for activities that deal with authentication state.
 */
public abstract class AuthActivity extends AppCompatActivity implements AuthObserver.Callback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AuthObserver authObserver = new AuthObserver(getLifecycle(), this);
        getLifecycle().addObserver(authObserver);
    }

    /**
     * Signs out the current user.
     */
    public final void signOut() {
        onSignOut();
        FirebaseAuth.getInstance().signOut();
    }

    /**
     * Override this method to do signing-out pre-process such as UI update or signing out of federated identity
     * provider.
     */
    protected void onSignOut() {}

    @Override
    public void onSignedIn(@NonNull FirebaseUser user) {}

    @Override
    public void onSignedOut() {}
}