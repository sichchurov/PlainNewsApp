package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shchurovsi.plainnewsapp.databinding.FragmentSearchingNewsBinding
import com.shchurovsi.plainnewsapp.presentation.NewsActivity

class SearchingNewsFragment : Fragment() {

    private var _binding: FragmentSearchingNewsBinding? = null
    private val binding: FragmentSearchingNewsBinding
        get() = _binding ?: throw RuntimeException("SearchingNewsFragment is null!")

    override fun onAttach(context: Context) {
        (activity as NewsActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
