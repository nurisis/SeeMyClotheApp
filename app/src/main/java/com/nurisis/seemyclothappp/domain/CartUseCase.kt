package com.nurisis.seemyclothappp.domain

import android.net.Uri
import androidx.lifecycle.LiveData
import com.nurisis.seemyclothappp.data.local.Cart
import com.nurisis.seemyclothappp.data.repository.CartRepository
import java.text.SimpleDateFormat
import java.util.*

class CartUseCase (
    private val cartRepository: CartRepository
) {

    suspend fun addCartFromWeb(url:String, title:String, imageUrl:String) {
        cartRepository.addCart(
            Cart(
                cart_url = url,
                cart_title = title,
                cart_img_url = imageUrl,
                cart_created = SimpleDateFormat("YYYY_MM_dd_HH:mm:ss").format(Date())
            )
        )
    }

    suspend fun addCartFromShare(localImagePath:String) {
        cartRepository.addCart(
            Cart(
                cart_img_local_path = localImagePath,
                cart_created = SimpleDateFormat("YYYY_MM_dd_HH:mm:ss").format(Date())
            )
        )
    }

    fun getCartList() : LiveData<List<Cart>> {
        return cartRepository.getCartList()
    }
}