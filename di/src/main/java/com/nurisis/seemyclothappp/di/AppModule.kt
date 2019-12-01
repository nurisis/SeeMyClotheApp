package com.nurisis.seemyclothappp.di

import com.nurisis.seemyclothappp.data.repository.SearchRepository
import org.koin.dsl.module

val appModule = module {
    single { SearchRepository(get()) }
}