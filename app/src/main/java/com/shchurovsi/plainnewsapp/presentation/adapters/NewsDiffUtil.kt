package com.shchurovsi.plainnewsapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto

object NewsDiffUtil : DiffUtil.ItemCallback<ArticleDto>() {
    override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
        return oldItem == newItem
    }
}
