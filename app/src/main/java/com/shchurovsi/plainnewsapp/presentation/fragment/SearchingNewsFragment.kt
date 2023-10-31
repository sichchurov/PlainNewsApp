package com.shchurovsi.plainnewsapp.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shchurovsi.plainnewsapp.databinding.FragmentSearchingNewsBinding
import com.shchurovsi.plainnewsapp.presentation.NewsActivity
import com.shchurovsi.plainnewsapp.presentation.NewsViewModel
import com.shchurovsi.plainnewsapp.presentation.ViewModelFactory
import com.shchurovsi.plainnewsapp.presentation.adapters.NewsAdapter
import com.shchurovsi.plainnewsapp.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchingNewsFragment : Fragment() {

    private var _binding: FragmentSearchingNewsBinding? = null
    private val binding: FragmentSearchingNewsBinding
        get() = _binding ?: throw RuntimeException("SearchingNewsFragment is null!")

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
        _binding = FragmentSearchingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputQueryNews()

        getSearchingNews()

        setupRecyclerView()


        newsAdapter.setOnItemClickListener { article ->
            findNavController().navigate(
                SearchingNewsFragmentDirections
                    .actionSearchingNewsFragmentToArticleFragment(article)
            )
        }
    }

    private fun inputQueryNews() {
        var job: Job? = null
        binding.edSearchingNews.addTextChangedListener { editable ->
            job?.cancel()
            editable?.let {
                job = lifecycleScope.launch {
                    delay(TEXT_DELAY_SEARCHING_NEWS)
                    if (editable.toString().isNotEmpty()) {
                        binding.chooseCategory.visibility = View.GONE
                        viewModel.getSearchingNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun getSearchingNews() {
        viewModel.searchingNews.observe(viewLifecycleOwner) { response ->
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

    private fun getCategoryNews() {
        viewModel.searchingNews.observe(viewLifecycleOwner) { response ->
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

    private fun setupRecyclerView() = with(binding.searchingNewsRecycler) {
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TEXT_DELAY_SEARCHING_NEWS = 500L
        private const val TAG = "SearchingNewsFragment"
    }
}
