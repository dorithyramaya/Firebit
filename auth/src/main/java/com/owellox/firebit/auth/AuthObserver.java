package com.owellox.firebit.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthObserver {
    private final FirebaseAuth.AuthStateListener authStateListener;
    private final AuthObserver.Callback callback;
    private final Lifecycle lifecycle;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void connect() {
        if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            FirebaseAuth.getInstance().addAuthStateListener(this.authStateListener);
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public final void disconnect() {
        FirebaseAuth.getInstance().removeAuthStateListener(this.authStateListener);
    }

    public AuthObserver(@NonNull AuthObserver.Callback callback, @NonNull Lifecycle lifecycle) {
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

    public static final class Callback {
        public final void onSignedIn(@NonNull FirebaseUser user) {}

        public final void onSignedOut() {}
    }
}