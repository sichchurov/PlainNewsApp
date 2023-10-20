package com.shchurovsi.plainnewsapp.utils.countrypicker

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.domain.entities.Country
import com.shchurovsi.plainnewsapp.utils.countrypicker.adapter.CountryPickerAdapter
import okio.IOException
import java.util.Collections


class CountryPicker(
    private val context: Context
) {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val allCountries = getAllCountries()

    private val adapter by lazy {
        CountryPickerAdapter(context, allCountries)
    }

    fun attach(bottomSheet: LinearLayout): CountryPicker {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        setUpRecyclerView(bottomSheet)
        return this
    }

    fun show(): CountryPicker {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        return this
    }

    fun dismiss(): CountryPicker {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        return this
    }

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

    private fun setUpRecyclerView(bottomSheet: LinearLayout) {
        val recyclerView = bottomSheet.findViewById<RecyclerView>(R.id.country_picker_recycler)
        recyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "CountryPicker"
    }
}
