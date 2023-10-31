package com.shchurovsi.plainnewsapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shchurovsi.plainnewsapp.databinding.FragmentChooseCategoryBinding
import com.shchurovsi.plainnewsapp.domain.entities.Categories

class ChooseCategoryFragment : Fragment() {

    private var _binding: FragmentChooseCategoryBinding? = null
    private val binding: FragmentChooseCategoryBinding
        get() = _binding ?: throw RuntimeException("ChooseCategoryFragment is null!")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseCategoryBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        launch()
    }

    /*private fun launch() = with(binding) {
        ivScienceNews.setOnClickListener {
            launchFragment(Categories.SCIENCE)
        }
        ivTechnologyNews.setOnClickListener {
            launchFragment(Categories.TECHNOLOGY)
        }
    }

    private fun launchFragment(categories: Categories) {
        findNavController().navigate(
            ChooseCategoryFragmentDirections.actionChooseCategoryFragmentToBreakingNewsFragment(
                categories
            )
        )
    }*/
}
