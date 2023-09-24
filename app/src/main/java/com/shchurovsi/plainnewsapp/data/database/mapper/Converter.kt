package com.shchurovsi.plainnewsapp.data.database.mapper

import androidx.room.TypeConverter
import com.shchurovsi.plainnewsapp.data.database.model.SourceDbModel


class Converter {

    @TypeConverter
    fun fromSource(sourceDbModel: SourceDbModel) = sourceDbModel.name

    @TypeConverter
    fun toSource(name: String) = SourceDbModel(name, name)
}