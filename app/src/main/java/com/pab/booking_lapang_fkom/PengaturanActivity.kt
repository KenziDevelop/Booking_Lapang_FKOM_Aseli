package com.pab.booking_lapang_fkom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pab.booking_lapang_fkom.databinding.ActivityPengaturanBinding

class PengaturanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaturanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}