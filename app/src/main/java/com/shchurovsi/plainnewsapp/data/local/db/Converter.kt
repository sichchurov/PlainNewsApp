package com.shchurovsi.plainnewsapp.data.local.db

import androidx.room.TypeConverter
import com.shchurovsi.plainnewsapp.data.local.model.SourceDbModel
import javax.inject.Inject


class Converter @Inject constructor() {

    @TypeConverter
    fun fromSource(sourceDbModel: SourceDbModel) = sourceDbModel.name

    @TypeConverter
    fun toSource(name: String) = SourceDbModel(name, name)
}