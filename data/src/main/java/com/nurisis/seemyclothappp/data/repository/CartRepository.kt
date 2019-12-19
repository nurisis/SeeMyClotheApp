package com.nurisis.seemyclothappp.data.repository

import com.nurisis.seemyclothappp.data.local.Cart
import com.nurisis.seemyclothappp.data.local.CartDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository (
    private val cartDao: CartDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
)  {

    suspend fun addCart(cart:Cart) = withContext(ioDispatcher) {
        cartDao.insertCart(cart)
    }

    suspend fun getCartList() = withContext(ioDispatcher) {
        cartDao.getAllCart()
    }
}
