package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shchurovsi.plainnewsapp.databinding.FragmentSavedNewsBinding
import com.shchurovsi.plainnewsapp.presentation.NewsActivity
import com.shchurovsi.plainnewsapp.presentation.NewsViewModel
import com.shchurovsi.plainnewsapp.presentation.ViewModelFactory
import com.shchurovsi.plainnewsapp.presentation.adapters.NewsAdapter
import javax.inject.Inject

class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding: FragmentSavedNewsBinding
        get() = _binding ?: throw RuntimeException("SavedNewsFragment is null!")

    private val newsAdapter by lazy {
        NewsAdapter(requireActivity())
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: NewsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        (activity as NewsActivity).appComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.savedArticles.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.savedArticleRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
