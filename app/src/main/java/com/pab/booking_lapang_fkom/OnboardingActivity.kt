package com.pab.booking_lapang_fkom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pab.booking_lapang_fkom.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPagerAdapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Daftar layout onboarding kamu (sesuaikan nama file layoutnya)
        val onboardingScreens = listOf(
            R.layout.fragment_onboarding1,     // Screen 1: Intro BookLapang (background biru)
            R.layout.onboarding_screen2,       // Screen 2: Booking Mudah
            R.layout.onboarding_screen3,       // Screen 3: Real-time Availability
            R.layout.onboarding_screen4        // Screen 4: Konfirmasi Instan
        )

        viewPagerAdapter = OnboardingAdapter(onboardingScreens)
        binding.viewPager.adapter = viewPagerAdapter

        // Hubungkan indicator titik
        TabLayoutMediator(binding.tabIndicator, binding.viewPager) { _, _ -> }.attach()

        // Tombol Lewati
        binding.btnSkip.setOnClickListener {
            finishOnboarding()
        }

        // Tombol Lanjut / Mulai
        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < onboardingScreens.size - 1) {
                binding.viewPager.currentItem += 1
            } else {
                finishOnboarding()
            }
        }

        // Ubah teks tombol di screen terakhir
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == onboardingScreens.size - 1) {
                    binding.btnNext.text = "Mulai >"
                } else {
                    binding.btnNext.text = "Lanjut >"
                }
            }
        })
    }

    private fun finishOnboarding() {
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("onboarding_shown", true).apply()

        // Pindah ke LoginActivity (pastikan nama class-nya benar)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}