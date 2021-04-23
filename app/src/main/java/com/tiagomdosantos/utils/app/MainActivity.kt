package com.tiagomdosantos.utils.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiagomdosantos.utils.app.databinding.ActivityMainBinding
import com.tiagomdosantos.utils.lib.extensions.bindLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupUi()
    }

    // region --- SETUP ---
    private fun setupBinding() {
        binding = bindLayout<ActivityMainBinding>(R.layout.activity_main)
    }

    private fun setupUi() {
        supportActionBar?.hide()
    }
    // endregion
}