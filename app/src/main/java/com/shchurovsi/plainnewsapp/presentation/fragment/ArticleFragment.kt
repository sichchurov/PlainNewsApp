package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.shchurovsi.plainnewsapp.R
import com.shchurovsi.plainnewsapp.presentation.NewsActivity

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val args by navArgs<ArticleFragmentArgs>()

    override fun onAttach(context: Context) {
        (activity as NewsActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        view.findViewById<WebView>(R.id.web_view).apply {
            webViewClient = WebViewClient()
            loadUrl(article)
        }


    }

}
