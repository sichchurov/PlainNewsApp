package com.shchurovsi.plainnewsapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.databinding.ActivityNewsBinding
import com.shchurovsi.plainnewsapp.databinding.CountryPickerLayoutBinding
import com.shchurovsi.plainnewsapp.utils.countrypicker.CountryPicker
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsBinding.inflate(layoutInflater)
    }

    val appComponent by lazy {
        (application as NewsApplication).applicationComponent.activityComponent().create()
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>



    private val countryPickerBinding by lazy {
        CountryPickerLayoutBinding.inflate(layoutInflater)
    }

    private lateinit var countryPicker: CountryPicker

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        setupBottomSheet()

        setupBottomNavigation()
    }


    private fun setupBottomSheet() {

    }

    private fun setupBottomNavigation() {
        val fragmentViewId = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        val navController = fragmentViewId?.findNavController()
            ?: throw RuntimeException("Navcontroller doesn't exists")

        binding.bottomNavigation.setupWithNavController(navController)
    }
}