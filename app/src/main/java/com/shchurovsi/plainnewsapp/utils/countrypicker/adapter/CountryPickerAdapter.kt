package com.shchurovsi.plainnewsapp.utils.countrypicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.emoji.text.EmojiCompat
import androidx.recyclerview.widget.ListAdapter
import com.shchurovsi.plainnewsapp.databinding.CountryItemLayoutBinding
import com.shchurovsi.plainnewsapp.domain.entities.Country

class CountryPickerAdapter : ListAdapter<Country, CountryPickerViewHolder>(DiffCallback) {

    private var onCountrySelectedListener: ((Country) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryPickerViewHolder {
        return CountryPickerViewHolder(
            CountryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryPickerViewHolder, position: Int) {
        val countryItem = getItem(position)
        holder.binding.apply {
            val emoji = EmojiCompat.get().process(countryItem.emoji ?: "")
            val countryText = "${emoji.toString()} ${countryItem.name} "
            tvCountryName.text = countryText
            tvCountryName.tag = countryItem.code
            root.setOnClickListener {
                onCountrySelectedListener?.let {
                    it(countryItem)
                }
            }
        }
    }

    fun setOnSelectedCountryListener(listener: (Country) -> Unit) {
        onCountrySelectedListener = listener
    }
}
