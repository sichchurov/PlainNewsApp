package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shchurovsi.plainnewsapp.databinding.FragmentBreakingNewsBinding
import com.shchurovsi.plainnewsapp.presentation.NewsActivity
import com.shchurovsi.plainnewsapp.presentation.NewsViewModel
import com.shchurovsi.plainnewsapp.presentation.ViewModelFactory
import com.shchurovsi.plainnewsapp.presentation.adapters.NewsAdapter
import com.shchurovsi.plainnewsapp.utils.Resource
import javax.inject.Inject

class BreakingNewsFragment : Fragment() {

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding: FragmentBreakingNewsBinding
        get() = _binding ?: throw RuntimeException("BreakingNewsFragment is null!")

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
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getBreakingNews()

        newsAdapter.setOnItemClickListener { article ->
            findNavController().navigate(
                BreakingNewsFragmentDirections
                    .actionBreakingNewsFragmentToArticleFragment(article)
            )
        }
    }

    private fun getBreakingNews() {
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun setupRecyclerView() {
        binding.breakingNewsRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "BreakingNewsFragment"
    }

}
