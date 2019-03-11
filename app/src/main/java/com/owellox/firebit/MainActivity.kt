package com.owellox.firebit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser
import com.owellox.firebit.auth.AuthObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val authCallback = object: AuthObserver.Callback {
            override fun onSignedIn(user: FirebaseUser) {}

            override fun onSignedOut() {}
        }
        val authObserver = AuthObserver(lifecycle, authCallback)
        lifecycle.addObserver(authObserver)
    }
}