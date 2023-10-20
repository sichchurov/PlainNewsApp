package com.shchurovsi.plainnewsapp.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.shchurovsi.plainnewsapp.di.ApplicationScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

const val PREFERENCE_NAME = "settings"

@ApplicationScope
class DataStoreRepository @Inject constructor(
    private val application: Application
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    private object PreferenceKey {
        val country = stringPreferencesKey("country")
    }

    suspend fun saveToDataStore(country: String) {
        application.dataStore.edit { preference ->
            preference[PreferenceKey.country] = country
        }
    }

    val readCountryFromDataStore: Flow<String> = application.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("TAG", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            val country = preference[PreferenceKey.country] ?: "us"
            Log.d("TAG", "Country: $country")
            country
        }
}
