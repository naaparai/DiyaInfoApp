package com.example.diyainfoapp.fragment.ticket

import androidx.lifecycle.ViewModel
import com.example.diyainfoapp.R
import com.example.diyainfoapp.databinding.TicketFragmentBinding
import com.example.diyainfoapp.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketFragment : BaseFragment<TicketFragmentBinding>() {
    private val viewModel: TicketViewModel by viewModel()
    override fun getViewModel(): ViewModel = viewModel
    override fun getLayoutId() = R.layout.ticket_fragment
    override fun shouldShowBottomNavigation() = true
    override fun getMainToolbar() = Pair(true, getString(R.string.tickets))

}