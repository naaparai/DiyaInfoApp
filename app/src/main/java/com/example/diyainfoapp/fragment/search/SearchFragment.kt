package com.example.diyainfoapp.fragment.search

import androidx.lifecycle.ViewModel
import com.example.diyainfoapp.R
import com.example.diyainfoapp.databinding.SearchFragmentBinding
import com.example.diyainfoapp.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchFragmentBinding>() {
    private val viewModel: SearchViewModel by viewModel()
    override fun getViewModel(): ViewModel = viewModel
    override fun getLayoutId() = R.layout.search_fragment
    override fun shouldShowBottomNavigation() = true
    override fun getMainToolbar() = Pair(true, getString(R.string.search))

}