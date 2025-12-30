package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.Splashscreen3Binding

class SplashScreen3Activity : AppCompatActivity() {

    private lateinit var binding: Splashscreen3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Splashscreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnStart.text = "Mulai >"

        binding.btnStart.setOnClickListener {
            goToLogin()
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, SplashScreen2Activity::class.java))
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