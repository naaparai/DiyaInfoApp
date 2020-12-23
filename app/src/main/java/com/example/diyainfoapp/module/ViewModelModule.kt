package com.example.diyainfoapp.module

import com.example.diyainfoapp.fragment.home.HomeViewModel
import com.example.diyainfoapp.fragment.search.SearchViewModel
import com.example.diyainfoapp.fragment.ticket.TicketViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { TicketViewModel() }
    viewModel { SearchViewModel() }
}
