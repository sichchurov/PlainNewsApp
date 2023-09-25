package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shchurovsi.plainnewsapp.databinding.FragmentArticleNewsBinding
import com.shchurovsi.plainnewsapp.presentation.NewsActivity

class ArticleNewsFragment : Fragment() {

    private var _binding: FragmentArticleNewsBinding? = null
    private val binding: FragmentArticleNewsBinding
        get() = _binding ?: throw RuntimeException("ArticleNewsFragment is null!")

    override fun onAttach(context: Context) {
        (activity as NewsActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
