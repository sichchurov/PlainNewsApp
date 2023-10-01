package com.shchurovsi.plainnewsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.domain.usecases.DeleteArticleUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.GetBreakingNewsUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.GetSavedArticlesUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.InsertArticleUseCase
import com.shchurovsi.plainnewsapp.domain.usecases.SearchingNewsUseCase
import com.shchurovsi.plainnewsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val insertArticleUseCase: InsertArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val searchingNewsUseCase: SearchingNewsUseCase,
    private val getBreakingNewsUseCase: GetBreakingNewsUseCase
) : ViewModel() {

    private val _breakingNews = MutableLiveData<Resource<NewsResponseDto>>()
    val breakingNews: LiveData<Resource<NewsResponseDto>>
        get() = _breakingNews

    private val _searchingNews = MutableLiveData<Resource<NewsResponseDto>>()
    val searchingNews: LiveData<Resource<NewsResponseDto>>
        get() = _searchingNews

    private var breakingNewsPage = 1
    private var searchingNewsPage = 1

    init {
        _breakingNews.postValue(Resource.Loading())
        getBreakingNews()
    }

    private fun getBreakingNews(countryCode: String = COUNTRY_CODE) = viewModelScope.launch {
        val response = getBreakingNewsUseCase(countryCode, breakingNewsPage)
        _breakingNews.value = handleNewsResponse(response)
    }

    fun getSearchingNews(query: String, pageSize: Int = PAGE_SIZE) = viewModelScope.launch {
        val response = searchingNewsUseCase(query, searchingNewsPage, pageSize)
        _searchingNews.value = handleNewsResponse(response)
    }

    private fun handleNewsResponse(response: Response<NewsResponseDto>) =
        responseDtoResource(response)

    private fun responseDtoResource(response: Response<NewsResponseDto>): Resource<NewsResponseDto> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    companion object {
        private const val COUNTRY_CODE = "us"
        private const val PAGE_SIZE = 50
    }
}
