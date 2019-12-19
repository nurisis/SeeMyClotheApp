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

    // Item that user click to see detail

    private val _searchState = MutableLiveData<State>(State.START)
    val searchState : LiveData<State> = _searchState
    // List of search result
    private val _searchList = MutableLiveData<List<NaverShopItem>>()
    val searchList : LiveData<List<NaverShopItem>> = _searchList

    // Item that user click to see detail
    private val _clickedItem = MutableLiveData<NaverShopItem>()
    val clickedItem : LiveData<NaverShopItem> = _clickedItem

    private val _totalItem = MutableLiveData<Long>()
    val totalItem : LiveData<Long> = _totalItem

    private val _sharedImageUri = MutableLiveData<Uri>()
    val sharedImageUri : LiveData<Uri> = _sharedImageUri

    // List of my cart
    var cartList : LiveData<List<Cart>> = MutableLiveData()

    private val _searchLoading = MutableLiveData<Boolean>()
    val searchLoading : LiveData<Boolean> = _searchLoading

    init {
        viewModelScope.launch {
            cartList = cartUseCase.getCartList()
        }
    }

    // Search the query
    fun search(query:String) {
        if(query.isEmpty()) return

        _searchLoading.value = true
        _searchState.value = State.SHOW

        viewModelScope.launch {
            val searchResult = searchClothUseCase.search(query)
            Log.d("LOG>>", "Search result : $searchResult")

            if(searchResult is Result.Success) {
                _searchList.value = searchResult.data.items
                _totalItem.value = searchResult.data.total
            }
            else
                _searchState.value = State.NONE

            _searchLoading.value = false
        }
    }

    fun clickItem(item:NaverShopItem) {
        _clickedItem.value = item
    }

    fun setSharedImagePath(uri:Uri?) {
        if(uri == null) {
            _toastMsg.value = "No image shared.."
            return
        }

        _sharedImageUri.value = uri
    }

    /**
     * Store the item locally the user adds to the shopping cart in the webview
     * */
    fun addToCartFromShare(uri:Uri?) {
        if(uri == null || uri.path == null) {
            _toastMsg.value = "No image information!"
            return
        }

        _sharedImageUri.value = uri
        viewModelScope.launch {
            cartUseCase.addCartFromShare(uri.path!!)
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

    fun getCartList() {
        viewModelScope.launch {
            cartList = cartUseCase.getCartList()
        }
    }

    /**
     * Get an uri from shared intent from MainActivity
     * */

}