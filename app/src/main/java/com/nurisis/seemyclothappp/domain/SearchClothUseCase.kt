package com.nurisis.seemyclothappp.domain

import com.nurisis.seemyclothappp.data.NaverSearchResult
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.data.repository.SearchRepository

class SearchClothUseCase (
    private val searchRepository: SearchRepository
) {

    suspend fun search(query:String) : Result<NaverSearchResult> {
        val result = searchRepository.searchCloth(query)

        return result
    }
}