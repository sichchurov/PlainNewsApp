package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.presentation.NewsActivity
import com.shchurovsi.plainnewsapp.presentation.NewsViewModel
import com.shchurovsi.plainnewsapp.presentation.ViewModelFactory
import javax.inject.Inject

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val args by navArgs<ArticleFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: NewsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        (activity as NewsActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        view.findViewById<WebView>(R.id.web_view).apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        view.findViewById<FloatingActionButton>(R.id.FAB).setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article: ${article.title} has been added", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

}
