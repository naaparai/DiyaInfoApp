package com.example.diyainfoapp.fragment.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.diyainfoapp.R
import com.example.diyainfoapp.databinding.HomeFragmentBinding
import com.example.diyainfoapp.fragment.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewModel(): ViewModel = viewModel

    override fun getLayoutId() = R.layout.home_fragment

    override fun shouldShowBottomNavigation() = true

    override fun getMainToolbar() = Pair(true, getString(R.string.home))

}