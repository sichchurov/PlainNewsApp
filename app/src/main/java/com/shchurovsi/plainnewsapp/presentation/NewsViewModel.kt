package com.shchurovsi.plainnewsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.entities.Categories
import com.shchurovsi.plainnewsapp.domain.entities.NewsResponse
import com.shchurovsi.plainnewsapp.domain.usecases.DeleteArticleUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.GetBreakingNewsUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.GetCategoryNewsUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.GetSavedArticlesUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.InsertArticleUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.SearchingNewsUseCase
import com.shchurovsi.plainnewsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val insertArticleUseCase: InsertArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val searchingNewsUseCase: SearchingNewsUseCase,
    private val getBreakingNewsUseCase: GetBreakingNewsUseCase,
    private val getCategoryNewsUseCase: GetCategoryNewsUseCase
) : ViewModel() {

    private lateinit var newCategories: Categories

    fun setCategory(categories: Categories) {
        newCategories = categories
    }

    private val _breakingNews = MutableLiveData<Resource<NewsResponse>>()
    val breakingNews: LiveData<Resource<NewsResponse>>
        get() = _breakingNews

    private val _searchingNews = MutableLiveData<Resource<NewsResponse>>()
    val searchingNews: LiveData<Resource<NewsResponse>>
        get() = _searchingNews

    val savedArticles: LiveData<List<Article>>
        get() = getSavedArticlesUseCase()

    private var breakingNewsPage = 1
    private var searchingNewsPage = 1

    init {
        _breakingNews.postValue(Resource.Loading())
        getBreakingNews()
    }

    private fun getBreakingNews(
        countryCode: String = COUNTRY_CODE
    ) = viewModelScope.launch {
        try {
            val response = getBreakingNewsUseCase(
                countryCode,
                breakingNewsPage
            )
            _breakingNews.value = Resource.Success(response)
        } catch (ioe: IOException) {
            _breakingNews.value =
                Resource.Error("[IO] error please retry, ${ioe.message}")
        } catch (he: HttpException) {
            _breakingNews.value =
                Resource.Error("[HTTP] error please retry, ${he.message}")
        }

    }

    private fun getNewsByCategory(
        countryCode: String = COUNTRY_CODE,
        newsCategory: String = newCategories.value
    ) = viewModelScope.launch {
        try {
            val response = getCategoryNewsUseCase(
                countryCode,
                breakingNewsPage,
                newsCategory
            )
            _breakingNews.value = Resource.Success(response)
        } catch (ioe: IOException) {
            _breakingNews.value =
                Resource.Error("[IO] error please retry, ${ioe.message}")
        } catch (he: HttpException) {
            _breakingNews.value =
                Resource.Error("[HTTP] error please retry, ${he.message}")
        }

    }

    fun getSearchingNews(query: String, pageSize: Int = PAGE_SIZE) = viewModelScope.launch {
        try {
            val response = searchingNewsUseCase(query, searchingNewsPage, pageSize)
            _searchingNews.value = Resource.Success(response)
        } catch (ioe: IOException) {
            _searchingNews.value =
                Resource.Error("[IO] error please retry, ${ioe.message}")
        } catch (he: HttpException) {
            _searchingNews.value =
                Resource.Error("[HTTP] error please retry, ${he.message}")
        }

    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        insertArticleUseCase(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteArticleUseCase(article)
    }

    companion object {
        private const val COUNTRY_CODE = "us"
        private const val PAGE_SIZE = 50
    }
}
