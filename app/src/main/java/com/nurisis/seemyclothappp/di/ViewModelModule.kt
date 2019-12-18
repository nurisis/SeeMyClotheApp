package com.nurisis.seemyclothappp.di

import com.nurisis.seemyclothappp.ui.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShopViewModel(get(), get()) }
}