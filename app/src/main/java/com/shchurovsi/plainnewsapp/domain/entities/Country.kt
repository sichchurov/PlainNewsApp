package com.shchurovsi.plainnewsapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String?,
    val code: String?,
    val phoneCode: String?,
    val emoji: String?
) : Parcelable
