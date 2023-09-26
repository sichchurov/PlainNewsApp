package com.shchurovsi.plainnewsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto
import com.shchurovsi.plainnewsapp.databinding.ItemArticleBinding

class NewsAdapter : ListAdapter<ArticleDto, NewsViewHolder>(NewsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.binding.apply {
            Glide.with(root).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((ArticleDto) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArticleDto) -> Unit) {
        onItemClickListener = listener
    }
}
