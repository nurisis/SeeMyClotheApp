package com.nurisis.seemyclothappp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurisis.seemyclothappp.data.NaverShopItem
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.domain.SearchClothUseCase
import kotlinx.coroutines.launch

class ShopViewModel(
    private val searchClothUseCase: SearchClothUseCase
) : ViewModel() {

    private val _toastMsg = MutableLiveData<String>()
    val toastMsg : LiveData<String> = _toastMsg

    // List of search result
    private val _searchList = MutableLiveData<List<NaverShopItem>>()
    val searchList : LiveData<List<NaverShopItem>> = _searchList

    private val _totalItem = MutableLiveData<Long>()
    val totalItem : LiveData<Long> = _totalItem

    private val _searchLoading = MutableLiveData<Boolean>()
    val searchLoading : LiveData<Boolean> = _searchLoading

    // Search the query
    fun search(query:String) {
        if(query.isEmpty()) return

        _searchLoading.value = true
        viewModelScope.launch {
            val searchResult = searchClothUseCase.search(query)
            Log.d("LOG>>", "Search result : $searchResult")

            if(searchResult is Result.Success) {
                _searchList.value = searchResult.data.items
                _totalItem.value = searchResult.data.total
            }

            _searchLoading.value = false
        }
    }

}