package com.shchurovsi.plainnewsapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val description: String?,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String?
) : Parcelable
