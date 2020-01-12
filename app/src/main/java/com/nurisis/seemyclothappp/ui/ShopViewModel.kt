package com.nurisis.seemyclothappp.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurisis.seemyclothappp.data.NaverShopItem
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.data.local.Cart
import com.nurisis.seemyclothappp.domain.CartUseCase
import com.nurisis.seemyclothappp.domain.SearchClothUseCase
import com.nurisis.seemyclothappp.entity.State
import kotlinx.coroutines.launch

class ShopViewModel(
    private val searchClothUseCase: SearchClothUseCase,
    private val cartUseCase: CartUseCase
) : ViewModel() {

    private val _toastMsg = MutableLiveData<String>()
    val toastMsg : LiveData<String> = _toastMsg

    private val _searchState = MutableLiveData<State>(State.START)
    val searchState : LiveData<State> = _searchState

    // List of search result
    private val _searchList = MutableLiveData<List<NaverShopItem>>()
    val searchList : LiveData<List<NaverShopItem>> = _searchList

    // Item that user click to see detail
    private val _clickedItem = MutableLiveData<NaverShopItem>()
    val clickedItem : LiveData<NaverShopItem> = _clickedItem

    // Total number of items of search list
    private val _totalItem = MutableLiveData<Long>()
    val totalItem : LiveData<Long> = _totalItem

    private val _sharedToBookmarkDone = MutableLiveData<Boolean>(false)
    val sharedToBookmarkDone : LiveData<Boolean> = _sharedToBookmarkDone

    // List of my cart
    var cartList : LiveData<List<Cart>> = cartUseCase.getCartList()

    private val _searchLoading = MutableLiveData<Boolean>()
    val searchLoading : LiveData<Boolean> = _searchLoading

    // Search the query
    fun search(query:String) {
        if(query.isEmpty()) return

        _searchLoading.value = true
        _searchState.value = State.SHOW

        viewModelScope.launch {
            searchClothUseCase.search(query).run {
                if(this is Result.Success) {
                    _searchList.value = data.items
                    _totalItem.value = data.total
                }
                else
                    _searchState.value = State.NONE
            }

            _searchLoading.value = false
        }
    }

    fun clickItem(item:NaverShopItem) {
        _clickedItem.value = item
    }

    /**
     * Store the item locally the user adds to the shopping cart in the webview
     * */
    fun addToCartFromShare(uri:Uri?, title:String) {
        if(title.isEmpty()) {
            _toastMsg.value = "Please write a title."
            return
        }
        if(uri == null || uri.path == null) {
            _toastMsg.value = "No image information!"
            return
        }

        viewModelScope.launch {
            cartUseCase.addCartFromShare(uri.normalizeScheme().toString(), title)
            _sharedToBookmarkDone.value = true
            _toastMsg.value = "Save it to your cart! \uD83D\uDE4C"
        }

    }

    /**
     * Store the item locally the user adds to the shopping cart in the webview
     * */
    fun addToCartFromWebView(url:String, title:String, imageUrl:String) {
        if(url.isEmpty()) {
            _toastMsg.value = "There is no url to add to the cart."
            return
        }

        viewModelScope.launch {
            cartUseCase.addCartFromWeb(url, title, imageUrl)
            _toastMsg.value = "Save it to your cart! \uD83D\uDE4C"
        }
    }

    fun clickCartItem(cart:Cart){
        if(cart.cart_url.isNotEmpty())
            _clickedItem.value = NaverShopItem(link = cart.cart_url)
        else
            _toastMsg.value = "No url to go."
    }

}