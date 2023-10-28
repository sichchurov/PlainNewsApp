package com.shchurovsi.plainnewsapp.utils.countrypicker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shchurovsi.plainnewsapp.databinding.CountryPickerLayoutBinding
import com.shchurovsi.plainnewsapp.domain.entities.Country
import com.shchurovsi.plainnewsapp.utils.countrypicker.adapter.CountryPickerAdapter
import okio.IOException

class CountryPicker : Fragment() {

    private var _countryPickerBinding: CountryPickerLayoutBinding? = null
    private val countryPickerBinding: CountryPickerLayoutBinding
        get() = _countryPickerBinding ?: throw RuntimeException("CountryPicker is null!")

    private val countryPickerAdapter by lazy {
        CountryPickerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _countryPickerBinding = CountryPickerLayoutBinding.inflate(
            inflater, container, false
        )
        return countryPickerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        countryPickerAdapter.submitList(getAllCountries())
        selectCountry()
    }


    private fun selectCountry() {
        countryPickerAdapter.setOnSelectedCountryListener {
            Log.d("TAG", "Country: ${it.code}")
        }
    }

    private fun getAllCountries(): List<Country> {
        val countries = mutableListOf<Country>()

        try {
            val inputStream = resources.assets.open("countries.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val json = String(buffer, Charsets.UTF_8)
            val gson = Gson()
            val listType = object : TypeToken<List<Country?>?>() {}.type
            val listCountry: List<Country> = gson.fromJson(json, listType)
            countries.addAll(listCountry)
        } catch (e: IOException) {
            Log.d(TAG, "Exception: ${e.message}")
        }
        return countries.toList()
    }

    private fun setUpRecyclerView() {
        countryPickerBinding.countryPickerRecycler.apply {
            adapter = countryPickerAdapter
        }
    }

    companion object {
        private const val TAG = "CountryPicker"

        fun newInstance(): CountryPicker {
            return CountryPicker()
        }
    }
}
