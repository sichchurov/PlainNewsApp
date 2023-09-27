package com.shchurovsi.plainnewsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.databinding.ActivityNewsBinding
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsBinding.inflate(layoutInflater)
    }

    val appComponent by lazy {
        (application as NewsApplication).applicationComponent.activityComponent().create()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

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