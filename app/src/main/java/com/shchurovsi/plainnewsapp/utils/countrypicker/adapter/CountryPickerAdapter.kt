package com.shchurovsi.plainnewsapp.utils.countrypicker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.recyclerview.widget.RecyclerView
import com.shchurovsi.plainnewsapp.databinding.CountryItemLayoutBinding
import com.shchurovsi.plainnewsapp.domain.entities.Country

class CountryPickerAdapter(
    context: Context,
    private val countryList: List<Country>) :
    RecyclerView.Adapter<CountryPickerViewHolder>() {

    // Initialize EmojiCompat with bundled font support (no need for a FontRequest)

    init {
        val config = BundledEmojiCompatConfig(context)
        EmojiCompat.init(config)
    }

    private var onCountrySelectedListener: ((Country) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryPickerViewHolder {
        return CountryPickerViewHolder(
            CountryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryPickerViewHolder, position: Int) {
        val countryItem = countryList[position]
        holder.binding.apply {
            countryItem.let {
                val emoji = EmojiCompat.get().process(countryItem.emoji ?: "")
                val countryText = "$emoji ${countryItem.name} "
                tvCountryName.text = countryText
                tvCountryName.tag = countryItem.code
                root.setOnClickListener {
                    onCountrySelectedListener?.let {
                        it(countryItem)
                    }
                }
            }
        }
    }

    fun setOnSelectedCountryListener(listener: (Country) -> Unit) {
        onCountrySelectedListener = listener
    }
}
