package com.nurisis.seemyclothappp.domain

import androidx.lifecycle.LiveData
import com.nurisis.seemyclothappp.data.NaverSearchResult
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.data.local.Cart
import com.nurisis.seemyclothappp.data.repository.CartRepository
import com.nurisis.seemyclothappp.data.repository.SearchRepository
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CartUseCase (
    private val cartRepository: CartRepository
) {

    suspend fun addCartFromWeb(url:String, title:String, imageUrl:String) {
        cartRepository.addCartFromWeb(
            Cart(
                cart_url = url,
                cart_title = title,
                cart_img_url = imageUrl,
                cart_created = SimpleDateFormat("YYYY_MM_dd_HH:mm:ss").format(Date())
            )
        )
    }

    suspend fun getCartList() : LiveData<List<Cart>> {
        return cartRepository.getCartList()
    }
}