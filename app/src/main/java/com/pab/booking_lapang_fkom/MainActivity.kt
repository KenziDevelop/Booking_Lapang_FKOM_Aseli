package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(android.R.layout.activity_list_item) // cukup pakai layout bawaan Android

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val onboardingDone = prefs.getBoolean("onboarding_done", false)

        val targetActivity = if (onboardingDone) {
            LoginActivity::class.java
        } else {
            OnboardingIntroActivity::class.java
        }

        startActivity(Intent(this, targetActivity))
        finish()
    }
}