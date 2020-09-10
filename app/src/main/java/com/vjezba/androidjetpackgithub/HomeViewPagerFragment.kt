package com.vjezba.androidjetpackgithub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.vjezba.androidjetpackgithub.adapters.ALL_GITHUBS
import com.vjezba.androidjetpackgithub.adapters.GithubPagerAdapter
import com.vjezba.androidjetpackgithub.adapters.SAVED_GITHUB_REPOSITORIES
import com.vjezba.androidjetpackgithub.databinding.FragmentViewPagerBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = GithubPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        //(activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            SAVED_GITHUB_REPOSITORIES -> R.drawable.garden_tab_selector
            ALL_GITHUBS -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            SAVED_GITHUB_REPOSITORIES -> "Saved language".toUpperCase()
            ALL_GITHUBS -> "All languages".toUpperCase()
            else -> null
        }
    }
}