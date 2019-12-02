package com.nurisis.seemyclothappp.di

import com.nurisis.seemyclothappp.data.repository.SearchRepository
import com.nurisis.seemyclothappp.domain.SearchClothUseCase
import org.koin.dsl.module

val appModule = module {
    single { SearchRepository(get()) }
    single { SearchClothUseCase(get()) }
}