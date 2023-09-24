package com.shchurovsi.plainnewsapp.di

import com.shchurovsi.plainnewsapp.presentation.NewsActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: NewsActivity)
}
