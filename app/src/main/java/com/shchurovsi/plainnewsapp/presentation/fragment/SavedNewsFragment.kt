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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

        setupSwipeListener()

        setupRecyclerView()

        observe()


        newsAdapter.setOnItemClickListener { article ->
            findNavController().navigate(
                SavedNewsFragmentDirections
                    .actionSavedNewsFragmentToArticleFragment(article)
            )
        }
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

    private fun setupSwipeListener() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val article = newsAdapter.currentList[position]
                Log.d("TAG", "Article ID: ${article.id}")
                viewModel.deleteArticle(article)

                Snackbar.make(binding.root, "Successfully deleted article!", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            viewModel.saveArticle(article)
                        }
                    }.show()
            }
        }).attachToRecyclerView(binding.savedArticleRecycler)
    }
}
