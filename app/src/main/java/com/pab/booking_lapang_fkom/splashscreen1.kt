package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.Splashscreen1Binding

class SplashScreen1Activity : AppCompatActivity() {

    private lateinit var binding: Splashscreen1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Splashscreen1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, SplashScreen2Activity::class.java))
            finish()
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, OnboardingIntroActivity::class.java))
            finish()
        }

        binding.btnSkip.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin() {
        getSharedPreferences("app_prefs", MODE_PRIVATE)
            .edit()
            .putBoolean("onboarding_done", true)
            .apply()

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}