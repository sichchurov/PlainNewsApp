package com.shchurovsi.plainnewsapp.di

import androidx.lifecycle.ViewModel
import com.shchurovsi.plainnewsapp.presentation.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("WARNINGS")
@Module
interface ViewModelModule {

    @ViewModelKey(NewsViewModel::class)
    @IntoMap
    @Binds
    fun bindNewsViewModel(impl: NewsViewModel): ViewModel
}
