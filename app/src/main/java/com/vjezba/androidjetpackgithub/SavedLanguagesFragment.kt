package com.vjezba.androidjetpackgithub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.vjezba.androidjetpackgithub.adapters.ALL_GITHUBS
import com.vjezba.androidjetpackgithub.adapters.SavedLanguagesAdapter
import com.vjezba.androidjetpackgithub.databinding.FragmentSavedLanguagesBinding
import com.vjezba.androidjetpackgithub.utilities.InjectorUtils
import com.vjezba.androidjetpackgithub.viewmodels.SavedLanguagesListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SavedLanguagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedLanguagesFragment : Fragment() {

    private lateinit var binding: FragmentSavedLanguagesBinding

    private val viewModel: SavedLanguagesListViewModel by viewModels {
        InjectorUtils.provideSavedLanguagesViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedLanguagesBinding.inflate(inflater, container, false)
        val adapter = SavedLanguagesAdapter()
        binding.savedLanguageList.adapter = adapter

        binding.addLanguage.setOnClickListener {
            navigateToPlantListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: SavedLanguagesAdapter, binding: FragmentSavedLanguagesBinding) {
        viewModel.savedAndAllLanguages.observe(viewLifecycleOwner) { result ->
            binding.hasLanguages = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            ALL_GITHUBS
    }
}