package com.shchurovsi.plainnewsapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.databinding.ActivityNewsBinding
import com.shchurovsi.plainnewsapp.utils.countrypicker.SettingsPicker
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

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Initialize EmojiCompat
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)

        setupBottomSheet()

        setupBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.choose_country -> {
                SettingsPicker().show(supportFragmentManager, "Country")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBottomSheet() {
        viewModel.currentCountry.observe(this) {
            Log.d("TAG", "CODE: $it")
        }
    }

    private fun setupBottomNavigation() {
        val fragmentViewId = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        val navController = fragmentViewId?.findNavController()
            ?: throw RuntimeException("Navcontroller doesn't exists")

        binding.bottomNavigation.setupWithNavController(navController)
    }

}
