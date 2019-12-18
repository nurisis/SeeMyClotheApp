package com.nurisis.seemyclothappp.data.repository

import android.util.Log
import com.nurisis.seemyclothappp.data.NaverSearchApi
import com.nurisis.seemyclothappp.data.NaverSearchResult
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.data.base.BaseRepository
import com.nurisis.seemyclothappp.data.local.Cart
import com.nurisis.seemyclothappp.data.local.CartDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CartRepository (
    private val cartDao: CartDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
)  {

    suspend fun addCartFromWeb(cart:Cart) = withContext(ioDispatcher) {
        cartDao.insertCartFromWeb(cart)
    }

    suspend fun getCartList() = withContext(ioDispatcher) {
        cartDao.getAllCart()
    }
}
