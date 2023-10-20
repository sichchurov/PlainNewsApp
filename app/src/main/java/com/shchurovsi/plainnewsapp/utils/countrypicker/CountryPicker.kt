package com.shchurovsi.plainnewsapp.utils.countrypicker

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shchurovsi.plainnewsapp.databinding.CountryPickerLayoutBinding
import com.shchurovsi.plainnewsapp.domain.entities.Country
import com.shchurovsi.plainnewsapp.utils.countrypicker.adapter.CountryPickerAdapter
import okio.IOException


class CountryPicker(private val context: Context) : DialogFragment() {

    private val countryPickerAdapter by lazy {
        CountryPickerAdapter()
    }

    private val allCountries = getAllCountries()

    private var _countryPickerBinding: CountryPickerLayoutBinding? = null
    private val countryPickerBinding
        get() = _countryPickerBinding ?: throw RuntimeException("CountryPicker is null!")

    interface ConfirmationListener {
        fun confirmButtonClick(country: String)
    }

    private lateinit var listener: ConfirmationListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _countryPickerBinding = CountryPickerLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        return countryPickerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()


        countryPickerAdapter.submitList(allCountries)

    }


    /**
     * Get all countries from countries.json
     */
    private fun getAllCountries(): List<Country> {
        val countries = mutableListOf<Country>()

        try {
            val inputStream = context.assets.open("countries.json")
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
    }
}
