package com.example.vehiclemaintenancerebuild

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vehiclemaintenancerebuild.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}