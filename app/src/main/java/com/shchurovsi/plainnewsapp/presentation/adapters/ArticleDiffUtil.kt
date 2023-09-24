package com.shchurovsi.plainnewsapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.shchurovsi.plainnewsapp.domain.entities.Article

class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
