package com.shchurovsi.plainnewsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val fragmentViewId = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        val navController = fragmentViewId?.findNavController()
            ?: throw RuntimeException("Navcontroller doesn't exists")

        binding.bottomNavigation.setupWithNavController(navController)
    }
}