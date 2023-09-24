package com.shchurovsi.plainnewsapp.presentation

import android.app.Application
import com.shchurovsi.plainnewsapp.di.DaggerApplicationComponent

class NewsApplication : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
