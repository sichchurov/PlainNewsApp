package com.shchurovsi.plainnewsapp.di

import com.shchurovsi.plainnewsapp.presentation.NewsActivity
import com.shchurovsi.plainnewsapp.presentation.fragment.ArticleFragment
import com.shchurovsi.plainnewsapp.presentation.fragment.BreakingNewsFragment
import com.shchurovsi.plainnewsapp.presentation.fragment.SavedNewsFragment
import com.shchurovsi.plainnewsapp.presentation.fragment.SearchingNewsFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: NewsActivity)
    fun inject(fragment: ArticleFragment)
    fun inject(fragment: BreakingNewsFragment)
    fun inject(fragment: SavedNewsFragment)
    fun inject(fragment: SearchingNewsFragment)
}
