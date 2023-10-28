package com.shchurovsi.plainnewsapp.utils.countrypicker

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.databinding.CountryPickerLayoutBinding
import com.shchurovsi.plainnewsapp.databinding.SettingsLayoutBinding
import com.shchurovsi.plainnewsapp.domain.entities.Country
import com.shchurovsi.plainnewsapp.presentation.NewsViewModel
import com.shchurovsi.plainnewsapp.utils.countrypicker.adapter.CountryPickerAdapter
import okio.IOException


class SettingsPicker : DialogFragment() {


    private var _settingsBinding: SettingsLayoutBinding? = null
    private val settingsBinding
        get() = _settingsBinding ?: throw RuntimeException("CountryPicker is null!")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.apply {
            setGravity(Gravity.CENTER)

        }
        dialog.window?.attributes.apply {
            LayoutParams.WRAP_CONTENT
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _settingsBinding = SettingsLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openListOfCountries()

    }

    private fun openListOfCountries() {
        settingsBinding.tvSelectedCountry.setOnClickListener {
            childFragmentManager.apply {
                commit {
                    replace(R.id.fragment_container, CountryPicker.newInstance())
                }
            }
        }
    }
}
