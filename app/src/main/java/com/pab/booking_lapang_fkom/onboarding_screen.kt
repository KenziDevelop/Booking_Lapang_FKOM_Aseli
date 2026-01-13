package com.pab.booking_lapang_fkom

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.OnboardingScreenBinding

class OnboardingIntroActivity : AppCompatActivity() {

    private lateinit var binding: OnboardingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OnboardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SplashScreen1Activity::class.java)
            startActivity(intent)
            finish()
        }, 5000) // 5000 ms = 5 detik
    }
}