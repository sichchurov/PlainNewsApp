package com.shchurovsi.plainnewsapp.presentation

import androidx.lifecycle.ViewModel
import com.shchurovsi.plainnewsapp.domain.usecases.DeleteArticleUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.GetSavedArticlesUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.InsertArticleUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val insertArticleUseCase: InsertArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
) : ViewModel() {


}
