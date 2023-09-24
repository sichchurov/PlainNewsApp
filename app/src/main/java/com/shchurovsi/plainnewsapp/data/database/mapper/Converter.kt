package com.shchurovsi.plainnewsapp.data.database.mapper

import androidx.room.TypeConverter
import com.shchurovsi.plainnewsapp.data.database.model.Source


class Converter {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)
}