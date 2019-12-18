package com.nurisis.seemyclothappp.di

import com.nurisis.seemyclothappp.data.local.MyDatabase
import com.nurisis.seemyclothappp.data.repository.CartRepository
import com.nurisis.seemyclothappp.data.repository.SearchRepository
import com.nurisis.seemyclothappp.domain.CartUseCase
import com.nurisis.seemyclothappp.domain.SearchClothUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SearchRepository(get()) }
    single { SearchClothUseCase(get()) }

    single { CartRepository(MyDatabase.getDatabase(androidContext()).cartDao()) }
    single { CartUseCase(get()) }
}