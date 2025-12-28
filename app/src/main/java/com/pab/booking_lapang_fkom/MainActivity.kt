package com.pab.booking_lapang_fkom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val onboardingShown = prefs.getBoolean("onboarding_shown", false)

        if (onboardingShown) {
            // Langsung ke login kalau onboarding sudah pernah dilihat
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            // Tampilkan onboarding dulu
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
        finish() // Tutup MainActivity biar tidak kembali ke sini saat tekan back
    }
}