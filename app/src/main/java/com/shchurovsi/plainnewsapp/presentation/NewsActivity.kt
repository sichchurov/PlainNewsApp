package com.shchurovsi.plainnewsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.databinding.ActivityNewsBinding
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

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

        setSupportActionBar(binding.toolbar)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.breakingNewsFragment, R.id.savedNewsFragment, R.id.searchingNewsFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}
