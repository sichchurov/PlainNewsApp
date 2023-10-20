package com.shchurovsi.plainnewsapp.utils.countrypicker.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shchurovsi.plainnewsapp.domain.entities.Country

object DiffCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}