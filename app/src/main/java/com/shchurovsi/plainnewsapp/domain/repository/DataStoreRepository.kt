package com.shchurovsi.plainnewsapp.domain.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException


const val PREFERENCE_NAME = "settings"

class DataStoreRepository(private val context: Context) {

    private object PreferenceKey {
        val country = stringPreferencesKey("country")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    suspend fun saveToDataStore(country: String) {
        context.dataStore.edit { preference ->
            preference[PreferenceKey.country] = country
        }
    }

    val readCountryFromDataStore: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            val country = preference[PreferenceKey.country] ?: "ru"
            country
        }
}
